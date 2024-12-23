package com.superlapp.auth.application.service;

import io.vertx.ext.auth.impl.jose.JWT;

import java.util.List;
import java.util.UUID;
import io.quarkus.security.User;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
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

    public TokenService(UUID userId, String username, Set<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public String generate() {
        return Jwt
                .issuer("https://heelstrike.app")
                .subject(username)
                .groups((Set<String>) roles)
                .expiresIn(Duration.ofHours(2))
                .sign();
    }
}
