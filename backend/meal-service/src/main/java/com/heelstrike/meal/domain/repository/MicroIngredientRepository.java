package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.entity.MacroIngredientEntity;
import com.heelstrike.meal.domain.entity.MicroIngredientEntity;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class MicroIngredientRepository implements PanacheRepository<MicroIngredientEntity> {

    public Optional<MicroIngredientEntity> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public List<MicroIngredientEntity> findByDynamicQuery(String query, Map<String, Object> params) {
        return find(query, params).list();
    }

}
