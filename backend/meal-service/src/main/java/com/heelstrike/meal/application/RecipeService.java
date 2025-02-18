package com.heelstrike.meal.application;

// import com.heelstrike-core.BaseService;
import com.heelstrike.meal.domain.dto.*;
import com.heelstrike.meal.domain.entity.*;
import com.heelstrike.meal.domain.repository.*;
import com.heelstrike.meal.domain.mappers.RecipeDTOMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;

@ApplicationScoped
public class RecipeService {

    @Inject
    RecipeRepository recipeRepository;

    @Inject
    AllergenRepository allergenRepository;

    @Inject
    MicroIngredientRepository microIngredientRepository;

    @Inject
    MacroIngredientRepository macroIngredientRepository;

    @Inject
    DietRepository dietRepository;

    @Inject
    RecipeDTOMapper recipeDTOMapper;

    public RecipeService() {

    }

    public List<RecipeDTO> getAll() {
        List<RecipeEntity> recipes = recipeRepository.findAllEntities(RecipeEntity.class);

        return recipes.stream()
                .map(recipe -> new RecipeDTO(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getCookingInstructions(),
                        recipe.getRecipeImage() != null
                                ? new RecipeImageDTO(
                                recipe.getRecipeImage().getId(),
                                recipe.getRecipeImage().getImageUrl()
                        )
                                : null,
                        recipe.getDietSuitability().stream()
                                .map(diet -> new DietDTO(
                                        diet.getId(),
                                        diet.getName()
                                ))
                                .toList(),
                        recipe.getAllergens().stream()
                                .map(allergen -> new AllergenDTO(
                                        allergen.getId(),
                                        allergen.getName()
                                ))
                                .toList(),
                        recipe.getMacroIngredients().stream()
                                .map(macroIngredient -> new MacroIngredientDTO(
                                        macroIngredient.getId(),
                                        macroIngredient.getName(),
                                        macroIngredient.getAllergens()
                                ))
                                .toList(),
                        recipe.getMicroIngredients().stream()
                                .map(microIngredient -> new MicroIngredientDTO(
                                        microIngredient.getId(),
                                        microIngredient.getName()
                                ))
                                .toList()
                ))
                .toList();
    }

    public List<RecipeDTO> getByFilters(Integer id,
                                        String name,
                                        String cookingInstructions,
                                        String allergens,
                                        String dietarySuitability) {

        Map<String, Object> filters = new HashMap<>();
        filters.put("id", id);
        filters.put("name", name);
        filters.put("cooking_instructions", cookingInstructions);
        filters.put("allergens", allergens);
        filters.put("dietary_suitability", dietarySuitability);

        filters.entrySet().removeIf(entry -> entry.getValue() == null);

        Optional<List<RecipeEntity>> recipeOptional = recipeRepository.findEntity(RecipeEntity.class, filters);

        if (recipeOptional.isEmpty() || recipeOptional.get().isEmpty()) {
            return List.of();
        }

        return recipeOptional.get().stream()
                .map(recipe -> new RecipeDTO(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getCookingInstructions(),
                        recipe.getRecipeImage() != null
                                ? new RecipeImageDTO(
                                recipe.getRecipeImage().getId(),
                                recipe.getRecipeImage().getImageUrl()
                        )
                                : null,
                        recipe.getDietSuitability().stream()
                                .map(diet -> new DietDTO(
                                        diet.getId(),
                                        diet.getName()))
                                .toList(),
                        recipe.getAllergens().stream()
                                .map(allergen -> new AllergenDTO(
                                        allergen.getId(),
                                        allergen.getName()
                                ))
                                .toList(),
                        recipe.getMacroIngredients().stream()
                                .map(macroIngredients -> new MacroIngredientDTO(
                                        macroIngredients.getId(),
                                        macroIngredients.getName(),
                                        macroIngredients.getAllergens()
                                ))
                                .toList(),
                        recipe.getMicroIngredients().stream()
                                .map(microIngredients -> new MicroIngredientDTO(
                                        microIngredients.getId(),
                                        microIngredients.getName()
                                ))
                                .toList()
                ))
                .toList();
    }

    @Transactional
    public void addRecipe(RecipeDTO recipeDTO) {
        RecipeEntity newRecipe = new RecipeEntity();
        //newRecipe.setId(recipeDTO.getId());
        newRecipe.setName(recipeDTO.getName());

        Optional.ofNullable(recipeDTO.getRecipeImage())
                        .ifPresent(recipeImageDTO -> {
                            RecipeImageEntity recipeImage = new RecipeImageEntity();
                            recipeImage.setImageUrl(recipeImageDTO.getUrl());
                            newRecipe.setRecipeImage(recipeImage);
                        });

        newRecipe.setAllergens(recipeDTOMapper.mapToEntity(
                AllergenEntity.class,
                recipeDTO.getAllergens(),
                allergenRepository,
                "allergen"
        ));

        newRecipe.setMicroIngredients(recipeDTOMapper.mapToEntity(
                MicroIngredientEntity.class,
                recipeDTO.getMicroIngredients(),
                microIngredientRepository,
                "micro_ingredients"
        ));

        newRecipe.setMacroIngredients(recipeDTOMapper.mapToEntity(
                MacroIngredientEntity.class,
                recipeDTO.getMacroIngredients(),
                macroIngredientRepository,
                "macro_ingredients"
        ));

        newRecipe.setDietSuitability(recipeDTOMapper.mapToEntity(
                DietEntity.class,
                recipeDTO.getDietarySuitability(),
                dietRepository,
                "dietary_suitability"
        ));

        recipeRepository.saveEntity(newRecipe);

    }

    //TODO: Implement this.
    @Transactional
    public void updateRecipe(RecipeDTO recipeDTO) {

    }
}
