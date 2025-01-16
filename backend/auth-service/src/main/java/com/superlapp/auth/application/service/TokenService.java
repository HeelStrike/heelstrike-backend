package com.superlapp.auth.application.service;

import com.superlapp.auth.application.producers.TokenProducer;
import com.superlapp.auth.application.validation.AuthValidator;
import com.superlapp.auth.domain.repository.UserRepository;

import com.superlapp.auth.domain.dto.UserDTO;
import com.superlapp.auth.domain.entity.UserEntity;


import java.util.UUID;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    private UUID userUuid;
    private String username;
    private Set<String> roles;

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
        if (authValidator.validateUser()) {
            try {

                UserEntity userEntity = userRepository.findUserEntity(
                        userDTO.getUuid()
                );

                userUuid = tokenProducer.produceUserId(userEntity);
                username = tokenProducer.produceUsername(userEntity);
                roles = tokenProducer.produceRoles(userEntity);

                return Jwt
                        .issuer("https://heelstrike.app")
                        .subject(username)
                        .groups(roles)
                        .expiresIn(Duration.ofHours(2))
                        .sign();

            } catch (Exception e) {
                throw new RuntimeException("Failed to generate JWT, " + e);
            }
        }

        return ("User not found, unable to produce JWT.");
    }

}
