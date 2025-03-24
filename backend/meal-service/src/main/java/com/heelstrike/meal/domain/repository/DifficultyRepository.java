package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.entity.DifficultyEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class DifficultyRepository implements PanacheRepository<DifficultyEntity> {
    public Optional<DifficultyEntity> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
