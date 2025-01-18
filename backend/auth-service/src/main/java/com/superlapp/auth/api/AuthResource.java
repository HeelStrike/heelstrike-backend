package com.superlapp.auth.api;

import com.superlapp.auth.application.service.TokenService;
import com.superlapp.auth.application.validation.AuthValidator;
import com.superlapp.auth.domain.dto.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Path("/auth")
public class AuthResource {

    @Inject
    TokenService tokenService;

    @Inject
    UserDTO userDTO;

    @Inject
    AuthValidator authValidator;

    @POST
    @Path("/token")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Response giveToken(@QueryParam("username") String username) {

        if (!authValidator.validateUser(username)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invalid username, user: " + username + ", could not be found")
                    .build();
        }

        userDTO.setUsername(username);

        String token = tokenService.generate(userDTO);

        return Response.ok(token).build();
    }
}
