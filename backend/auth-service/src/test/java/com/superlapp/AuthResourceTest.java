package com.superlapp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AuthResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testJWTIssue() {
        String jwt = given()
                .queryParam("userId", UUID.randomUUID())
                .queryParam("username", "testUser")
                .queryParam("roles", "admin,user")
                .when()
                .post("/token")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        System.out.println("Generated JWT: " + jwt);

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());

        String[] jwtParts = jwt.split("\\.");
        assertEquals(3, jwtParts.length, "JWT must have 3 parts delimited by '.' ");

        String payload = new String(java.util.Base64.getUrlDecoder()
                .decode(jwtParts[1]), StandardCharsets.UTF_8);

        assertTrue(payload.contains("\"sub\":\"testUser\""));
        assertTrue(payload.contains("\"groups\":[\"admin\",\"user\"]"));
    }

}