package com.vinschool.smarttime.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class SecurityConfigReader {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    public SecurityConfigReader(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    public List<SecurityRule> readConfig(String configFilePath) throws IOException {
        Resource resource = resourceLoader.getResource(configFilePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<SecurityRule>>() {
            });
        }
    }

    // Inner class để đại diện cho một quy tắc bảo mật
    @Getter
    @Setter
    public static class SecurityRule {
        private String endpoint;
        private List<String> methods;
        private List<String> roles;
        private List<String> permissions;
        private String expression;
    }
}