package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.entity.MacroIngredientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MacroIngredientRepository implements PanacheRepository<MacroIngredientEntity> {
        public Optional<MacroIngredientEntity> findByName(String name) {
            return find("name", name).firstResultOptional();
        }
}
