package com.heelstrike;

import com.heelstrike.auth.api.AuthResource;
import com.heelstrike.auth.application.service.TokenService;
import com.heelstrike.auth.application.validation.AuthValidator;
import com.heelstrike.auth.domain.dto.UserDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusMock;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestHTTPEndpoint(AuthResource.class)
class GenerateTokenTest {

    private UserDTO userDTO;

    @Inject
    TokenService tokenService;

    private TokenService mockTokenService;

    @BeforeEach
    public void setup() {
        userDTO = new UserDTO();
        this.userDTO.setUsername("testuser88");
        this.userDTO.setPassword("SecureP4ssw0rd319!");
        this.userDTO.setPrimaryEmail("testuser@gmail.com");
        this.userDTO.setSecondaryEmail("test.user@workplace.uk");
        this.userDTO.setRoleId(1L);
        this.userDTO.setMobile(7713422893L);

        mockTokenService = mock(TokenService.class);
        QuarkusMock.installMockForType(mockTokenService, TokenService.class);

    }

    @Test
    public void testGenerateTokenEndpoint() {
        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post("api/auth/login")
                .then()
                .statusCode(200);

        verify(mockTokenService, times(1)).generate(any(UserDTO.class));
    }
}
