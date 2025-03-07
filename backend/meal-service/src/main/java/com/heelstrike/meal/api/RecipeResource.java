package com.heelstrike.meal.api;


import com.heelstrike.meal.application.RecipeService;
import com.heelstrike.meal.domain.dto.RecipeDTO;
import com.heelstrike.meal.domain.repository.RecipeRepository;
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

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    RecipeRepository recipeRepository;

    //TODO: Un-fuck this.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@QueryParam("id") Integer id,
                              @QueryParam("name") String name,
                              @QueryParam("cooking_instructions") String cookingInstructions,
                              @QueryParam("allergens") String allergens,
                              @QueryParam("dietary_suitability") String dietarySuitability) {

        //List<RecipeDTO> resultsList = recipeService.getAll();
        List<RecipeDTO> resultsList = recipeService.getByFilters(id, name, cookingInstructions, allergens, dietarySuitability);

        //if (securityIdentity.isAnonymous()) {
        //    return Response.status(Response.Status.UNAUTHORIZED).build();
        //} else {
        //    return Response.status(Response.Status.ACCEPTED).build();
        //}
        if (resultsList != null && !resultsList.isEmpty()) {
            return Response.ok(resultsList)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        return Response.ok(Map.of("message", "No results found."))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addRecipe(RecipeDTO recipeDTO) {
        recipeService.addRecipe(recipeDTO);
        return Response.ok(recipeDTO).build();
    }
}
