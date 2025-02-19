package com.heelstrike.auth.api;

import com.heelstrike.auth.application.service.TokenService;
import com.heelstrike.auth.application.service.UserService;
import com.heelstrike.auth.application.validation.AuthValidator;
import com.heelstrike.auth.domain.dto.UserDTO;
import com.heelstrike.auth.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/auth")
@ApplicationScoped
public class AuthResource {

    UserDTO userDTO;

    @Inject
    TokenService tokenService;

    @Inject
    AuthValidator authValidator;

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

    public AuthResource() {
        UserDTO userDTO = new UserDTO();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response giveToken(UserDTO userDTO) {

        if (!authValidator.validateUser(userDTO.getUsername())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invalid username, user: " + userDTO.getUsername() + ", could not be found")
                    .build();
        }

        if (!authValidator.validatePassword(userDTO.getUsername(), userDTO.getPassword())) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Invalid password, user: " + userDTO.getUsername())
                    .build();
        }

        String token = tokenService.generate(userDTO);

        return Response.ok(token).build();
    }

    //TODO: Add query field for setting RBAC permissions.
    @POST
    @Path("/create-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(UserDTO userDTO) {

        if (authValidator.validateUser(userDTO.getUsername())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("User: " + userDTO.getUsername() + ", already exists!")
                    .build();
        }

        userService.createUser(userDTO);

        return Response.ok(userDTO).build();
    }

    @POST
    @Path("/update-user")
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@QueryParam("username") String username) {

        userDTO.setUsername(username);

        try {
            userService.deleteUser(userDTO);

            return Response.ok("User deleted successfully.").build();

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while deleting user, " + e)
                    .build();
        }
    }
}
