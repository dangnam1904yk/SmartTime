package com.vinschool.smarttime.model.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vinschool.smarttime.model.projection.UserProjection;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails {

    public UserPrincipal(UserProjection user) {
        this.user = user;
        this.fullName = user.getFullName();
    }

    public UserProjection user;
    private String fullName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCodeRole()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
