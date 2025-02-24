package com.heelstrike;

import com.heelstrike.auth.application.service.TokenService;
import com.heelstrike.auth.application.validation.AuthValidator;
import com.heelstrike.auth.domain.dto.UserDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class GenerateTokenTest {

    @BeforeAll
    static void setup() {
        TokenService mockTokenService = mock(TokenService.class);
        when(mockTokenService.generate(any(UserDTO.class)))
                .thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0VXNlciJ9.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        QuarkusMock.installMockForType(mockTokenService, TokenService.class);
    }
        @Test
        public void testGenerateTokenEndpoint() {
            String response = given()
                    .basePath("/api/auth")
                    .contentType("application/json")
                    .body("{\"username\": \"TestUser\", \"password\": \"TestPassword\"}")
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(200)
                    .extract().asString();
            System.out.println("Extracted JWT Token: " + response);
            assert response.matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+={0,2}$");
        }
    }

}
