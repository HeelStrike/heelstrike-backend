package com.heelstrike;

import com.heelstrike.auth.application.validation.AuthValidator;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;

@QuarkusTest
class AuthResourceTest {

    @BeforeAll
    static void setup() {
        AuthValidator mockValidator = mock(AuthValidator.class);
        when(mockValidator.validateUser(anyString())).thenReturn(true);

        QuarkusMock.installMockForType(mockValidator, AuthValidator.class);

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
    }

    @Test
    public void testGenerateTokenEndpoint() {
        String response = given()
                .basePath("/api/auth")
                .contentType("application/json")
                .body("{\"username\": \"TestUser\", \"password\": \"TestPassword\"}")
                .when()
                .post("/token")
                .then()
                .extract().asString(); // Extract the actual response

        System.out.println("Response: " + response);
    }

}
