package com.heelstrike.auth.api;

import com.heelstrike.auth.application.service.TokenService;
import com.heelstrike.auth.application.service.UserService;
import com.heelstrike.auth.application.validation.AuthValidator;
import com.heelstrike.auth.domain.dto.UserDTO;
import com.heelstrike.auth.domain.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/auth")
public class AuthResource {

    @Inject
    TokenService tokenService;

    @Inject
    UserDTO userDTO;

    @Inject
    AuthValidator authValidator;

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

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
                               @QueryParam("mobile") long mobile) {

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

        userService.createUser(userDTO);

        return Response.ok(userDTO).build();
    }

    @POST
    @Path("/update-user")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@QueryParam("uuid") UUID uuid,
                               @QueryParam("newUsername") String newUsername,
                               @QueryParam("newPassword") String newPassword,
                               @QueryParam("newPrimaryEmail") String newPrimaryEmail,
                               @QueryParam("newSecondaryEmail") String newSecondaryEmail,
                               @QueryParam("newMobile") @DefaultValue("-1") long newMobile) {

        userDTO.setUuid(uuid);
        userDTO.setUsername(newUsername);
        userDTO.setPassword(newPassword);
        userDTO.setPrimaryEmail(newPrimaryEmail);
        userDTO.setSecondaryEmail(newSecondaryEmail);
        userDTO.setMobile(newMobile);

        try {
            userService.updateUser(userDTO);
            return Response.ok()
                    .entity("User details updated successfully.")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Could not update user, " + e)
                    .build();
        }
    }


    @POST
    @Path("/delete-user")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@QueryParam("username") String username) {

        userDTO.setUsername(username);

        try {
            userService.deleteUser(userDTO);

            return Response.ok("User deleted successfully.").build();

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occured while deleting user, " + e)
                    .build();
        }
    }
}
