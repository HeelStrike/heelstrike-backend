package com.heelstrike.auth.application.validation;

import com.heelstrike.auth.application.service.PasswordService;
import com.heelstrike.auth.domain.entity.UserEntity;
import com.heelstrike.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    @Inject
    PasswordService passwordService;

    public boolean validateUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean validatePassword(String username, String password) {

        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

        String passwordHash = userEntityOptional.map(UserEntity::getPasswordHash)
                .orElseThrow(() -> new IllegalArgumentException("Could not get password hash for user"));

        return passwordService.verifyPassword(password, passwordHash);
    }
}
