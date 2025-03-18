package com.heelstrike.meal.api;


import com.heelstrike.meal.application.service.RecipeService;
import com.heelstrike.meal.domain.dto.RecipeDTO;
import com.heelstrike.meal.domain.dto.RecipeRequirementsDTO;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/recipe")
//@RolesAllowed("User")
public class RecipeResource {

    @Inject
    RecipeService recipeService;

    //@GET
    //@Path("/get")
    //@Consumes("application/json")
    //public Response getRecipe(RecipeDTO recipDTO) {
    //    RecipeDTO result = recipeService.get(RecipeDTO);
    //    return Response.ok(result).build();
    //}

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRecipeByRequirements(RecipeRequirementsDTO requirementsDTO) {
        List<RecipeEntity> recipes = recipeService.getRecipesByRequirements(requirementsDTO);

        if (!recipes.isEmpty()) {
            return Response.ok(recipes).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //@POST
    //@Path("/add")
    //@Consumes("application/json")
    //public Response addRecipe(RecipeDTO recipeDTO) {
    //    recipeService.addRecipe(recipeDTO);
    //    return Response.ok(recipeDTO).build();
    //}
}
