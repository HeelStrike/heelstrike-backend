package com.superlapp.auth.application.producers;

import com.superlapp.auth.domain.entity.RoleEntity;
import com.superlapp.auth.domain.entity.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class TokenProducer {

    @Inject
    UserEntity userEntity;

    public TokenProducer(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Produces
    public UUID produceUserId() {
        return userEntity.getUuid();
    }

    @Produces
    public String produceUsername() {
        return userEntity.getName();
    }

    @Produces
    public Set<String> produceRoles() {
        Set<String> roleSet = new java.util.HashSet<>(Collections.emptySet());
        List<RoleEntity> roleList = userEntity.getRoles();

        for (RoleEntity roleEntity : roleList) {
            roleSet.add(roleEntity.getName());
        }

        return roleSet;
    }
}
