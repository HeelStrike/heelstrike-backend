package com.superlapp.auth.application.validation;

import com.superlapp.auth.domain.entity.UserEntity;
import com.superlapp.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class AuthValidator {
    private UUID userUuid;
    private String username;
    private Set<String> roles;

    @Inject
    UserRepository userRepository;

    public boolean validateUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
