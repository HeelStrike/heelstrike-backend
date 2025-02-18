package com.heelstrike.auth.application.producers;

import com.heelstrike.auth.domain.entity.RoleEntity;
import com.heelstrike.auth.domain.entity.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class TokenProducer {

    public UUID produceUserId(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(UserEntity::getUuid)
                .orElseThrow(() -> new IllegalArgumentException("Could not get UUID, UserEntity not present."));
    }

    public String produceUsername(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(UserEntity::getUsername)
                .orElseThrow(() -> new IllegalArgumentException("Could not get username, UserEntity not present."));
    }

    public RoleEntity produceRoles(Optional<UserEntity> userEntityOptional) {

        return userEntityOptional.map(UserEntity::getRole)
                .orElseThrow(() -> new IllegalArgumentException("Could not get user role, UserEntity not present."));

    }
}
