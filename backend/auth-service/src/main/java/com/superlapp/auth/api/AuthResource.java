package com.superlapp.auth.api;

import com.superlapp.auth.application.service.TokenService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/token")
public class AuthResource {

    @Inject
    TokenService tokenService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response giveToken(@QueryParam("userId") UUID userId,
                              @QueryParam("username") String username,
                              @QueryParam("roles") List<String> roles) {

        tokenService = new TokenService(userId, username, roles);
        String token = tokenService.generate();

        return Response.ok(token).build();
    }
}
