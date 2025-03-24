package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.dto.*;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import com.heelstrike.meal.util.DurationConverter;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


@ApplicationScoped
public class RecipeRepository implements PanacheRepository<RecipeEntity> {

    public Optional<RecipeEntity> findById(long id) {
        return find("id", id).firstResultOptional();
    }

    public Optional<RecipeEntity> findByTitle(String title) {
        return find("title", title).firstResultOptional();
    }

    public List<RecipeEntity> findByDynamicQuery(String query, Map<String, Object> params) {
        return find(query, params).list();
    }

    @Transactional
    public void persistRecipeEntity(RecipeEntity recipeEntity) {
        try {
            persistAndFlush(recipeEntity);
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
