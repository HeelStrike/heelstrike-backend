package com.heelstrike.auth.application.service;

import com.heelstrike.auth.application.producers.TokenProducer;
import com.heelstrike.auth.application.validation.AuthValidator;
import com.heelstrike.auth.domain.entity.RoleEntity;
import com.heelstrike.auth.domain.repository.UserRepository;

import com.heelstrike.auth.domain.dto.UserDTO;
import com.heelstrike.auth.domain.entity.UserEntity;


import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.UUID;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Duration;

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
                    .claim("role", roleEntity.getName())
                    .expiresIn(Duration.ofMinutes(15))
                    .sign();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT, " + e.getMessage(), e);
        }
    }
}
