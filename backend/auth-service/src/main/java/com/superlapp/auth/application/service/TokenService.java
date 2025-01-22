package com.superlapp.auth.application.service;

import com.superlapp.auth.application.producers.TokenProducer;
import com.superlapp.auth.application.validation.AuthValidator;
import com.superlapp.auth.domain.entity.RoleEntity;
import com.superlapp.auth.domain.repository.UserRepository;

import com.superlapp.auth.domain.dto.UserDTO;
import com.superlapp.auth.domain.entity.UserEntity;


import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.UUID;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    @Inject
    PasswordService passwordService;

    private UUID userUuid;
    private String username;
    private RoleEntity roleEntity;

    @Inject
    UserRepository userRepository;

    @Inject
    AuthValidator authValidator;

    @Inject
    TokenProducer tokenProducer;

    @Inject
    UserDTO userDTO;

    public TokenService() {}

    public String generate(UserDTO userDTO) {
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDTO.getUsername());

            if (userEntityOptional.isEmpty()) {
                return "User not found, unable to produce JWT.";
            }

            try {
                passwordService.verifyPassword(
                        userDTO.getPasswordHash(),
                        userEntityOptional.map(UserEntity::getPasswordHash)
                                .orElseThrow(() -> new InvalidParameterException("Invalid password."))
                );

                userUuid = tokenProducer.produceUserId(userEntityOptional);
                username = tokenProducer.produceUsername(userEntityOptional);
                roleEntity = tokenProducer.produceRoles(userEntityOptional);

            } catch (Exception e) {
                throw new RuntimeException("Failed to generate JWT due to invalid password, " + e.getMessage(), e);
            }

            return Jwt
                    .issuer("https://heelstrike.app")
                    .subject(username)
                    .groups(roleEntity.getName())
                    .expiresIn(Duration.ofHours(2))
                    .sign();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT, " + e.getMessage(), e);
        }
    }
}
