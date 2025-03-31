package com.heelstrike.meal.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Set;

@Schema(description = "Recipe Data Transfer Object")
public class RecipeDTO {

    @Schema(examples = "3")
    private Long id;

    @Schema(examples = "Roast Beef with Garlic Roast Potatoes and Honey Roasted Carrots with Onion Gravy ")
    private String title;

    @Schema(examples = "A hearty and traditional Sunday roast dish, perfect for family gatherings.",
            description = "Short description of the recipe")
    private String description;

    @Schema(examples = "1. Preheat oven to 200°C.\n2. Season beef and roast for 1 hour.\n3. Toss potatoes in garlic and olive oil, roast until golden.\n4. Glaze carrots with honey and roast.\n5. Make onion gravy in a pan.\n6. Serve all together.",
            description = "Step-by-step cooking instructions")
    private String cookingInstructions;

    @Schema(examples = "1h 30m", description = "Total cooking time")
    private String cookingTime;

    @Schema(examples = "30m", description = "Preparation time before cooking")
    private String preparationTime;

    @Schema(examples = "4", description = "Number of servings")
    private Integer serves;

    @Schema(examples = "Intermediate", description = "Difficulty level of the recipe")
    private String difficulty;

    @Schema(description = "A major ingredient in a recipe, typically a combination of micro-ingredients (e.g., Tomato Pasata, Pesto, Chunky Chips etc...).")
    private Set<MacroIngredientDTO> macroIngredients;

    @Schema(description = "List of micro ingredients such as spices or condiments")
    private Set<MicroIngredientDTO> microIngredients;

    @Schema(description = "Allergens present in the recipe")
    private Set<AllergenDTO> allergens;

    @Schema(description = "Diets this recipe is suitable for (e.g., Gluten Free, Keto)")
    private Set<DietDTO> dietarySuitability;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getServes() {
        return this.serves;
    }

    public void setServes(Integer serves) {
        this.serves = serves;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Set<MacroIngredientDTO> getMacroIngredients() {
        return macroIngredients;
    }

    public void setMacroIngredients(Set<MacroIngredientDTO> macroIngredients) {
        this.macroIngredients = macroIngredients;
    }

    public Set<MicroIngredientDTO> getMicroIngredients() {
        return microIngredients;
    }

    public void setMicroIngredients(Set<MicroIngredientDTO> microIngredients) {
        this.microIngredients = microIngredients;
    }

    public Set<AllergenDTO> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(Set<AllergenDTO> allergens) {
        this.allergens = allergens;
    }

    public Set<DietDTO> getDietarySuitability() {
        return dietarySuitability;
    }

    public void setDietarySuitability(Set<DietDTO> dietarySuitability) {
        this.dietarySuitability = dietarySuitability;
    }

    @Override
    public String toString() {
        return "ID: " +
                this.id +
                " Title: " +
                this.title +
                " Description: " +
                this.description +
                " CookingInstructions: " +
                this.cookingInstructions +
                " CookingTime: " +
                this.cookingTime +
                " PreparationTime: " +
                this.preparationTime;
    }
}
