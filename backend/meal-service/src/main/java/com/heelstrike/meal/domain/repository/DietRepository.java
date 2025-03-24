package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.entity.DietEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class DietRepository implements PanacheRepository<DietEntity> {

    public Optional<DietEntity> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
