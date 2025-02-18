package com.heelstrike.auth.domain.repository;

import com.heelstrike.auth.domain.entity.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
//public class UserRepository implements PanacheRepositoryBase<UserEntity, Long>{
//TODO: Refactor to return an Optional data type. and also not to shit the bed when findUserEntity returns null.
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    public Optional<UserEntity> findByUuid(UUID uuid) {
        return find("uuid", uuid).firstResultOptional();
    }

    public Optional<UserEntity> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

    public Optional<UserEntity> findByPrimaryEmail(String primaryEmail) {
        return find("primary_email", primaryEmail).firstResultOptional(); //TODO: Change primaryEmail to UNIQUE in schema!
    }

    public Optional<UserEntity> findBySecondaryEmail(String secondaryEmail) {
        return find("secondary_email", secondaryEmail).firstResultOptional();
    }

    public void persistUserEntity(UserEntity userEntity) {
        try {
            persistAndFlush(userEntity);

            //TODO: Create custom  PersistenceException class that extends RuntimeException
        } catch (jakarta.validation.ConstraintViolationException e) {
            throw new jakarta.validation.ConstraintViolationException(
                    "Could not persist user entity in database due to validation error: " + e.getMessage(),
                    e.getConstraintViolations()
            );

        } catch (org.hibernate.exception.ConstraintViolationException e) {
            throw new org.hibernate.exception.ConstraintViolationException(
                    "Could not persist user entity in database due to database constraint violation: " + e.getMessage(),
                    e.getSQLException(),
                    e.getConstraintName()
            );
        }
    }
}
