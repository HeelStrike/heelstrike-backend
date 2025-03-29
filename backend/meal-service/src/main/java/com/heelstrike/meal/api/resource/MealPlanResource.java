package com.heelstrike.meal.api.resource;

import com.heelstrike.meal.application.service.MealPlanService;
import com.heelstrike.meal.domain.dto.MealPlanRequirementsDTO;
import com.heelstrike.meal.domain.dto.RecipeDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/meal-plan")
public class MealPlanResource {

    @Inject
    MealPlanService mealPlanService;

    @Path("/generate")
    @POST
    @Operation(summary = "Generate a meal plan", description = "Returns a List of Lists of Meals.")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns a List of Lists of Meals as JSON."),
            @APIResponse(responseCode = "404", description = "Meal Plan could not be found.")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateMealPlan(MealPlanRequirementsDTO requirements) {
        List<List<RecipeDTO>> mealPlan = mealPlanService.generate(requirements);

        return Response.ok(mealPlan).build();
    }

}
