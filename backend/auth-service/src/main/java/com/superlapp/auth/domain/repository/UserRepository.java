package com.superlapp.auth.domain.repository;

import com.superlapp.auth.domain.entity.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, Long>{
    //TODO: Refactor to return an Optional data type. and also not to shit the bed when findUserEntity returns null.

    public UserEntity findUserEntity(UUID userUuid) {
        System.out.println(userUuid);
        UserEntity userEntity = find("user_uuid", userUuid).firstResult();
        return userEntity;
    }
}
