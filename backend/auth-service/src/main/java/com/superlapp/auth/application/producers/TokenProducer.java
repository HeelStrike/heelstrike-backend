package com.superlapp.meal.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class TokenProducer<UserEntity> {

    @Inject
    UserEntity userEntity;

    @Produces
    public UUID produceUserId() {
        return userEntity.getId();
    }

    @Produces
    public String username() {
        return userEntity.getUsername();
    }

    @Produces
    public Set<String> roles() {
        return userEntity.getRoles();
    }
}
