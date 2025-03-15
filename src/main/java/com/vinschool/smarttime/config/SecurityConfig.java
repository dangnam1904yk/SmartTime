package com.vinschool.smarttime.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.vinschool.smarttime.service.CustomUserDetailsService;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final CustomUserDetailsService customUserDetailsService;

    private final SecurityConfigReader securityConfigReader;

    @Value("${security.config.file:classpath:security-config.json}")
    private String securityConfigFile;

    SecurityConfig(CustomUserDetailsService customUserDetailsService, SecurityConfigReader securityConfigReader) {
        this.customUserDetailsService = customUserDetailsService;
        this.securityConfigReader = securityConfigReader;
    }

    @PostConstruct
    public void init() {
        logger.info("SecurityConfig initialized.  CustomUserDetailsService: {}",
                customUserDetailsService.getClass().getName());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
            throws Exception {
        logger.info("Configuring SecurityFilterChain...");
        List<SecurityConfigReader.SecurityRule> rules = securityConfigReader.readConfig(securityConfigFile);

        http // .csrf(AbstractHttpConfigurer::disable)
                .csrf(Customizer.withDefaults())
                // .sessionManagement(session ->
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(authorize -> {
                    // Cấu hình từ file JSON trước
                    // for (SecurityConfigReader.SecurityRule rule : rules) {
                    // if (rule.getRoles().isEmpty()) {
                    // authorize.requestMatchers(rule.getEndpoint()).permitAll();
                    // } else {
                    // authorize.requestMatchers(rule.getEndpoint())
                    // .hasAnyAuthority(rule.getRoles().toArray(new String[0]));
                    // }
                    // }

                    for (SecurityConfigReader.SecurityRule rule : rules) {
                        String endpoint = rule.getEndpoint();
                        List<String> methods = rule.getMethods();

                        if (rule.getExpression() != null && !rule.getExpression().isEmpty()) {
                            authorize.requestMatchers(endpoint)
                                    .access(new WebExpressionAuthorizationManager(rule.getExpression()));
                        } else {
                            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
                            if (methods != null && !methods.isEmpty()) {
                                for (String methodStr : methods) {
                                    HttpMethod method = HttpMethod.valueOf(methodStr);
                                    // Cấu hình TRỰC TIẾP trong lambda, không gán cho biến
                                    if (rule.getRoles() != null && !rule.getRoles().isEmpty()) {
                                        authorize.requestMatchers(mvcMatcherBuilder.pattern(method, endpoint))
                                                .hasAnyAuthority(rule.getRoles().toArray(new String[0]));
                                    }
                                    if (rule.getPermissions() != null && !rule.getPermissions().isEmpty()) {
                                        AuthorizationManager<RequestAuthorizationContext> permissionManager = buildPermissionAuthorizationManager(
                                                rule.getPermissions());
                                        authorize.requestMatchers(mvcMatcherBuilder.pattern(method, endpoint))
                                                .access(permissionManager);
                                    }
                                    // Nếu không có role, chặn
                                    if (rule.getRoles() == null || rule.getRoles().isEmpty()) {
                                        authorize.requestMatchers(mvcMatcherBuilder.pattern(method, endpoint))
                                                .denyAll();
                                    }
                                }
                            } else {
                                // Cấu hình TRỰC TIẾP trong lambda, không gán cho biến
                                if (rule.getRoles() != null && !rule.getRoles().isEmpty()) {
                                    authorize.requestMatchers(mvcMatcherBuilder.pattern(endpoint))
                                            .hasAnyAuthority(rule.getRoles().toArray(new String[0]));
                                }
                                if (rule.getPermissions() != null && !rule.getPermissions().isEmpty()) {
                                    AuthorizationManager<RequestAuthorizationContext> permissionManager = buildPermissionAuthorizationManager(
                                            rule.getPermissions());
                                    authorize.requestMatchers(mvcMatcherBuilder.pattern(endpoint))
                                            .access(permissionManager);
                                }
                                // Nếu không có roles và permissions, chặn truy cập
                                if (rule.getRoles().isEmpty() && rule.getPermissions() == null
                                        && rule.getExpression() == null && rule.getMethods() == null) {
                                    authorize.requestMatchers(mvcMatcherBuilder.pattern(endpoint)).denyAll();
                                }
                            }
                        }
                    }
                    authorize
                            .requestMatchers("/dang-nhap", "/dang-ky", "/css/*", "/img/*", "/lib/*", "/js/*", "/scss/*")
                            .permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/dang-nhap").permitAll();
                    form.successHandler((request, response, authentication) -> {
                        logger.info("User {} logged in successfully", authentication.getName());
                        response.sendRedirect("/");
                    });
                    form.failureHandler((request, response, exception) -> {
                        logger.error("Login failed: {}", exception.getMessage());
                        response.sendRedirect("/dang-nhap?error");
                    });
                })
                .logout(out -> out.logoutSuccessUrl("/dang-nhap"));
        return http.build();
    }

    private AuthorizationManager<RequestAuthorizationContext> buildPermissionAuthorizationManager(
            List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return (a, o) -> new AuthorizationDecision(true);
            // return (a, o) -> new AuthorizationDecision(false); // denyAll
        }
        // hasPermission(targetObject, permission)
        AuthorizationManager<RequestAuthorizationContext> first = (authentication,
                object) -> new AuthorizationDecision(hasPermission(authentication.get(), null, permissions.get(0))); // targetObject
                                                                                                                     // =
                                                                                                                     // null

        if (permissions.size() == 1)
            return first;

        AuthorizationManager<RequestAuthorizationContext> result = first;
        for (int i = 1; i < permissions.size(); i++) {
            final int index = i; // Biến final để sử dụng trong lambda
            // Tạo một AuthorizationManager cho permission tiếp theo
            AuthorizationManager<RequestAuthorizationContext> next = (authentication,
                    object) -> new AuthorizationDecision(
                            hasPermission(authentication.get(), null, permissions.get(index)));

            // Kết hợp các AuthorizationManager bằng and (AuthorizationManagers.allOf)
            result = AuthorizationManagers.allOf(result, next);

        }

        return result;
    }

    // Thay thế bằng logic kiểm tra permission
    private boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        // Ví dụ: Kiểm tra xem authentication có authority "PERMISSION_" + permission
        // hay không
        String requiredPermission = "PERMISSION_" + permission;
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(requiredPermission));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(List.of(authProvider));
    }

}
