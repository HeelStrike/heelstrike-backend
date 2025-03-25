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
import java.util.Set;

@Path("/recipe")
//@RolesAllowed("User")
public class RecipeResource {

    @Inject
    RecipeService recipeService;

    //TODO: Obviously, refactor this so that title, difficulty etc... can be used as query parameters.
    /*
    * It's also worth highlighting, it's probably a good idea to decompose mapping functionality in recipeService.getRecipe
    * because it's
    * */
    @GET
    @Path("/get")
    @Consumes("application/json")
    public Response getRecipe(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("difficulty") String difficulty
    ) {
        RecipeDTO recipe = recipeService.getRecipe(id);

        if (recipe != null) {
            return Response.ok(recipe).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

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

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addRecipe(RecipeDTO recipeDTO) {
        recipeService.addRecipe(recipeDTO);
        return Response.ok(recipeDTO).build();
    }
}
