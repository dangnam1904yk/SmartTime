package com.vinschool.smarttime.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.vinschool.smarttime.controller.AccountScheduleController;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);
        // com.vinschool.smarttime.entity.User user = userService.findByEmail(username);
        var user = userRepository.findProjectionByEmail(username);
        if (user == null) {
            logger.error("User not found with email: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        logger.info("User found: {}", user.getEmail());
        user.getRole().size();
        return new UserPrincipal(user);
    }
}
