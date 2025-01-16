package com.superlapp.auth.api;

import com.superlapp.auth.application.service.TokenService;
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

    @POST
    @Path("/token")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public Response giveToken(@QueryParam("userUuid") UUID userUuid,
                              @QueryParam("username") String username,
                              @QueryParam("roles") Set<String> roles) {

        userDTO.setUuid(userUuid);
        userDTO.setName(username);
        userDTO.setRoles(roles);

        String token = tokenService.generate(userDTO);

        return Response.ok(token).build();
    }
}
