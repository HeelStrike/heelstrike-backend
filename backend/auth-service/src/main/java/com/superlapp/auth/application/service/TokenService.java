package com.superlapp.auth.application.service;

import com.superlapp.auth.application.producers.TokenProducer;
import io.vertx.ext.auth.impl.jose.JWT;

import com.superlapp.auth.domain.entity.UserEntity;

import java.util.List;
import java.util.UUID;
import io.quarkus.security.User;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.jshell.spi.ExecutionControl;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    private UUID userId;
    private String username;
    private Set<String> roles;

    @Inject
    UserEntity userEntity;

    @Inject
    TokenProducer tokenProducer;

    public TokenService(UserEntity userEntity) {
        this.userId = tokenProducer.produceUserId();
        this.username = tokenProducer.produceUsername();
        this.roles = tokenProducer.produceRoles();
    }

    public String generate() {
        return Jwt
                .issuer("https://heelstrike.app")
                .subject(username)
                .groups(roles)
                .expiresIn(Duration.ofHours(2))
                .sign();
    }
}
