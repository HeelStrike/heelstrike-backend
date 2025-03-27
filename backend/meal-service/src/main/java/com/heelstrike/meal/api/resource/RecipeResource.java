package com.heelstrike.meal.api.resource;


import com.heelstrike.meal.api.exception.ErrorResponse;
import com.heelstrike.meal.application.service.RecipeService;
import com.heelstrike.meal.domain.dto.RecipeDTO;
import com.heelstrike.meal.domain.dto.RecipeRequirementsDTO;
import com.heelstrike.meal.domain.entity.RecipeEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/recipe")
//@RolesAllowed("User")
public class RecipeResource {

    @Inject
    RecipeService recipeService;

    @POST
    @Path("/add")
    @Operation
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(RecipeDTO recipeDTO) {
        recipeService.addRecipe(recipeDTO);
        return Response.ok(recipeDTO).build();
    }

    //TODO: Obviously, refactor this so that title, difficulty etc... can be used as query parameters.
    /*
    * It's also worth highlighting, it's probably a good idea to decompose mapping functionality in recipeService.getRecipe
    * because it's
    * */
    @GET
    @Path("/get")
    @Operation(summary = "Get a recipe", description = "Returns a Recipe by its ID.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns a Recipe by its ID as JSON."),
            @APIResponse(responseCode = "404", description = "Recipe not found, likely not present within the database.")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRecipe(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("difficulty") String difficulty
    ) {
        RecipeDTO recipe = recipeService.getRecipe(id);

        if (recipe != null) {
            return Response.ok(recipe).build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("No recipes found."))
                .build();
    }

    @POST
    @Path("/search")
    @Operation(summary = "Search for a Recipe", description = "Returns List of Recipes based upon search criteria / requirements.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns List of Recipes."),
            @APIResponse(responseCode = "403", description = "Not found.")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRecipeByRequirements(
            @BeanParam RecipeRequirementsDTO requirementsDTO,
            @QueryParam("limit") @DefaultValue("20") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset,
            @QueryParam("sortBy") @DefaultValue("title") String sortBy,
            @QueryParam("sortOrder") @DefaultValue("asc") String sortOrder
    ) {
        List<RecipeEntity> recipes = recipeService.getRecipesByRequirements(requirementsDTO);

        if (!recipes.isEmpty()) {
            return Response.ok(recipes).build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("No recipes found by specified search criteria."))
                .build();
    }

    @PUT
    @Path("/update")
    @Operation(summary = "Updates a Recipe", description = "Updates a recipe.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns response containing DTO of updated recipe."),
    })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRecipe(RecipeDTO recipeDTO) {
        RecipeDTO updatedRecipeDTO = recipeService.updateRecipe(recipeDTO);

        return Response.ok(updatedRecipeDTO).build();
    }

    @DELETE
    @Path("/delete")
    @Operation(summary = "Delete a Recipe", description = "Deletes a recipe.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns response containing DTO of deleted recipe."),
            @APIResponse(responseCode = "403", description = "Not found.")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(RecipeDTO recipeDTO) {
        recipeService.deleteRecipe(recipeDTO);

        return Response.ok(recipeDTO + " deleted Successfully.").build();
    }
}
