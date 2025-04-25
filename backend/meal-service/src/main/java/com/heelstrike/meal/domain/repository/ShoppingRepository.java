package com.heelstrike.meal.domain.repository;

import com.heelstrike.meal.domain.entity.RecipeEntity;
import com.heelstrike.meal.domain.entity.ShopBoughtIngredientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Map;

public class ShoppingRepository implements PanacheRepository<ShopBoughtIngredientEntity> {

    public List<ShopBoughtIngredientEntity> findByDynamicQuery(String query, Map<String, Object> params) {
        return find(query, params).list();
    }
}
