package com.heelstrike.meal.application.service;

import com.heelstrike.meal.domain.dto.RecipeRequirementsDTO;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import com.heelstrike.meal.domain.repository.RecipeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.heelstrike.meal.domain.dto.RecipeDTO;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RecipeService {
    @Inject
    RecipeRepository recipeRepository;

    public List<RecipeEntity> getRecipesByRequirements(RecipeRequirementsDTO requirementsDTO) {
        return recipeRepository.findRecipesByRequirements(requirementsDTO);
    }
}
