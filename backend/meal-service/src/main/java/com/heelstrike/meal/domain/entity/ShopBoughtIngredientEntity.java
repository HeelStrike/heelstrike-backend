package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "shop_bought_ingredient")
public class ShopBoughtIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinTable(
            name = "shop_bought_ingredient_macro_ingredient",
            joinColumns = @JoinColumn(name = "shop_bought_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "macro_ingredient_id")
    )
    private Set<MacroIngredientEntity> linkedMacroIngredients;

    @OneToMany
    @JoinTable(
            name = "shop_bought_ingredient_micro_ingredient",
            joinColumns = @JoinColumn(name = "shop_bought_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "micro_ingredient_id")
    )
    private Set<MacroIngredientEntity> linkedMicroIngredients;

    @OneToMany
    @JoinTable(
            name = "shop_bought_ingredient_allergen",
            joinColumns = @JoinColumn(name = "shop_bought_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private Set<AllergenEntity> allergens;

    @ManyToMany
    @JoinTable(
            name = "shop_bought_ingredient_nutrient",
            joinColumns = @JoinColumn(name = "shop_bought_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "nutrient_id")
    )
    private Set<NutrientEntity> nutrients;

    @OneToMany
    @JoinTable(
            name = "shop_shop_bought_ingredient",
            joinColumns = @JoinColumn(name = "shop_bought_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id")
    )
    private Set<ShopEntity> shops;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MacroIngredientEntity> getLinkedMacroIngredients() {
        return linkedMacroIngredients;
    }

    public void setLinkedMacroIngredients(Set<MacroIngredientEntity> linkedMacroIngredients) {
        this.linkedMacroIngredients = linkedMacroIngredients;
    }

    public Set<MacroIngredientEntity> getLinkedMicroIngredients() {
        return linkedMicroIngredients;
    }

    public void setLinkedMicroIngredients(Set<MacroIngredientEntity> linkedMicroIngredients) {
        this.linkedMicroIngredients = linkedMicroIngredients;
    }

    public Set<ShopEntity> getShops() {
        return shops;
    }

    public void setShops(Set<ShopEntity> shops) {
        this.shops = shops;
    }

    public Set<AllergenEntity> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<AllergenEntity> allergens) {
        this.allergens = allergens;
    }

    public Set<NutrientEntity> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Set<NutrientEntity> nutrients) {
        this.nutrients = nutrients;
    }
}
