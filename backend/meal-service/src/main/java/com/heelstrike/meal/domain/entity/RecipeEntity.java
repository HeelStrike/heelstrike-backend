package com.heelstrike.meal.domain.entity;

import com.heelstrike.meal.util.DurationConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cooking_instructions")
    private String cookingInstructions;

    @Column(name = "preparation_time")
    private Float preparationTime;

    @Column(name = "cooking_time")
    private Float cookingTime;

    @Column(name = "serves")
    private int serves;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "difficulty_id")
    private DifficultyEntity difficulty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_macro_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "macro_ingredient_id")
    )
    private Set<MacroIngredientEntity> macroIngredients;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_micro_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "micro_ingredient_id")
    )
    private Set<MicroIngredientEntity> microIngredients;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_dietary_suitability",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private Set<DietEntity> dietarySuitability;

    public Long getId() {
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

    public Float getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Float preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Float getCookingTime() {
        return this.cookingTime;
    }

    public void setCookingTime(Float cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getServes() {
        return this.serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public DifficultyEntity getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEntity difficulty) {
        this.difficulty = difficulty;
    }

    public Set<MacroIngredientEntity> getMacroIngredients() {
        return this.macroIngredients;
    }

    public void setMacroIngredients(Set<MacroIngredientEntity> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public Set<DietEntity> getDietarySuitability() {
        return this.dietarySuitability;
    }

    public void setDietarySuitability(Set<DietEntity> dietarySuitability) {
        this.dietarySuitability = dietarySuitability;
    }
}
