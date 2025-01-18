package com.superlapp.auth.domain.repository;

import com.superlapp.auth.domain.entity.UserEntity;

import com.superlapp.core.BaseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
//public class UserRepository implements PanacheRepositoryBase<UserEntity, Long>{
    //TODO: Refactor to return an Optional data type. and also not to shit the bed when findUserEntity returns null.
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    public Optional<UserEntity> findByUuid(UUID uuid) {
        return find("user_uuid", uuid).firstResultOptional();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}
