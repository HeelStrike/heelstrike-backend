package com.heelstrike.meal.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column (name = "title")
    private String title;

    @Column (name = "description")
    private String description;

    @Column (name = "cooking_instructions")
    private String cookingInstructions;

    @Column (name = "preparation_time")
    private String preparationTime;

    @Column (name = "cooking_time")
    private String cookingTime;

    @Column (name = "serves")
    private int serves;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "micro_ingredient_macro_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "macro_ingredient_id")
    )
    private List<MacroIngredientEntity> macroIngredients;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_dietary_suitability",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private List<DietEntity> dietarySuitability;

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookingInstructions() {
        return this.cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public String getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getServes() {
        return this.serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public List<MacroIngredientEntity> getMacroIngredients() {
        return this.macroIngredients;
    }

    public void setMacroIngredients(List<MacroIngredientEntity> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public List<DietEntity> getDietarySuitability() {
        return this.dietarySuitability;
    }

    public void setDietarySuitability(List<DietEntity> dietarySuitability) {
        this.dietarySuitability = dietarySuitability;
    }

}
