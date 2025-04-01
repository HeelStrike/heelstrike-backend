package com.heelstrike.meal.domain.datastructure;

import com.heelstrike.meal.domain.entity.RecipeTypeEntity;

import java.util.List;
import java.util.Objects;

public class MealPlanCandidate {
    private List<Meal> breakfastMeals;
    private List<Meal> lunchMeals;
    private List<Meal> dinnerMeals;
    private List<Meal> snackMeals;
    private List<Meal> desertMeals;
    private List<Meal> allMeals;

    public MealPlanCandidate(List<Meal> meals) {
        this.allMeals = meals;
        if (allMeals != null) {
            addToMeal();
        }
    }

    public List<Meal> getAllMeals() {
        return allMeals;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.allMeals = allMeals;
    }

    public List<Meal> getBreakfastMeals() {
        return breakfastMeals;
    }

    public List<Meal> getLunchMeals() {
        return lunchMeals;
    }

    public List<Meal> getDinnerMeals() {
        return dinnerMeals;
    }

    public List<Meal> getSnackMeals() {
        return snackMeals;
    }

    public List<Meal> getDesertMeals() {
        return desertMeals;
    }

    public void addToMeal() {
        for (Meal meal : allMeals) {
            RecipeTypeEntity recipeType = meal.getRecipe().getRecipeType();

            if (Objects.equals(recipeType.getName(), "breakfast")) {
                Objects.requireNonNull(this.breakfastMeals).add(meal);
            }

            if (Objects.equals(recipeType.getName(), "lunch")) {
                Objects.requireNonNull(this.lunchMeals).add(meal);
            }

            if (Objects.equals(recipeType.getName(), "dinner")) {
                Objects.requireNonNull(this.dinnerMeals).add(meal);
            }

            if (Objects.equals(recipeType.getName(), "snack")) {
                Objects.requireNonNull(this.snackMeals).add(meal);
            }

            if (Objects.equals(recipeType.getName(), "desert")) {
                Objects.requireNonNull(this.desertMeals).add(meal);
            }
        }
    }
}
