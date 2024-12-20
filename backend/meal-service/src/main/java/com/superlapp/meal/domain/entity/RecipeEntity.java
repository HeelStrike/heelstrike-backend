package com.superlapp.meal.domain.entity;

import com.superlapp.core.BaseEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipe")
@ApplicationScoped
public class RecipeEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "recipe_name")
    private String name;

    @Column(name = "cooking_instructions", columnDefinition = "TEXT")
    private String cookingInstructions;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_image_id", foreignKey = @ForeignKey(name = "fk_recipe_image_id"))
    private RecipeImageEntity recipeImage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_allergen",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "id")
    )
    private List<AllergenEntity> allergens = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_nutrient",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutrient_id", referencedColumnName = "id")
    )
    private List<NutrientEntity> nutrients = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dietary_suitability",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private List<DietEntity> dietSuitability = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_micro_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "micro_ingredient_id", referencedColumnName = "id")
    )
    private List<MicroIngredientEntity> microIngredients = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_macro_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "macro_ingredient_id", referencedColumnName = "id")
    )
    private List<MacroIngredientEntity> macroIngredients = new ArrayList<>();

    public RecipeEntity() {

    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    public String getCookingInstructions() {
        return this.cookingInstructions;
    }

    public void setCookingInstructions(String newCookingInstructions) {
        this.cookingInstructions = newCookingInstructions;
    }

    public List<MacroIngredientEntity> getMacroIngredients() {
        return this.macroIngredients;
    }

    public List<MicroIngredientEntity> getMicroIngredients() {
        return this.microIngredients;
    }

    public List<DietEntity> getDietSuitability() {
        return this.dietSuitability;
    }

    public void setAllergens(List<AllergenEntity> newAllergens) {
        this.allergens = newAllergens;
    }

    public List<AllergenEntity> getAllergens() {
        return this.allergens;
    }

    public void setRecipeImage(RecipeImageEntity recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeImage() {
        return this.recipeImage.getImageUrl();
    }


}
