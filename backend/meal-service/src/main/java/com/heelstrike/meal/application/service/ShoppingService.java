package com.heelstrike.meal.application.service;

import com.heelstrike.meal.domain.entity.ShopBoughtIngredientEntity;

import java.util.List;

public class ShoppingService {

    private List<ShopBoughtIngredientEntity> shopBoughtIngredients;

    public List<ShopBoughtIngredientEntity> getShopBoughtIngredients() {
        return shopBoughtIngredients;
    }
}
