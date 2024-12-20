package com.superlapp.meal.domain.repository;

import com.superlapp.core.BaseRepository;
import com.superlapp.meal.domain.dto.AllergenDTO;
import com.superlapp.meal.domain.entity.MicroIngredientEntity;
import com.superlapp.meal.domain.entity.RecipeEntity;
import com.superlapp.meal.domain.dto.DietDTO;
import com.superlapp.meal.domain.dto.RecipeDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;

import java.util.List;

@ApplicationScoped
public class RecipeRepository extends BaseRepository<RecipeEntity> {


}
