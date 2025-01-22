package com.superlapp.auth.api;

import com.superlapp.auth.application.service.TokenService;
import com.superlapp.auth.application.service.UserCreationService;
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

    @Inject
    UserCreationService userCreationService;

    @POST
    @Path("/token")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Response giveToken(@QueryParam("username") String username,
                              @QueryParam("password") String password) {

        if (!authValidator.validateUser(username)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invalid username, user: " + username + ", could not be found")
                    .build();
        }

        userDTO.setUsername(username);
        userDTO.setPassword(password);

        String token = tokenService.generate(userDTO);

        return Response.ok(token).build();
    }

    //TODO: Add query field for setting RBAC permissions.
    @POST
    @Path("/create-user")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(@QueryParam("newUsername") String newUsername,
                               @QueryParam("newPassword") String newPassword,
                               @QueryParam("primaryEmail") String primaryEmail,
                               @QueryParam("mobile") long mobile
                               ) {

        if (authValidator.validateUser(newUsername)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("User: " + newUsername + ", already exists!")
                    .build();
        }

        userDTO.setUsername(newUsername);
        userDTO.setPassword(newPassword);
        userDTO.setPrimaryEmail(primaryEmail);
        userDTO.setMobile(mobile);
        //userDTO.setRole(1);

        userCreationService.createUser(userDTO);

        return Response.ok(userDTO).build();
    }
}
