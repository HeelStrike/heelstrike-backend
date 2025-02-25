package com.heelstrike;

import com.heelstrike.auth.domain.dto.UserDTO;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusIntegrationTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthResourceIT {

    private static final String BASE_PATH = "/api/auth";
    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        RestAssured.port = 8081;

        userDTO = new UserDTO();
        userDTO.setUsername("StevieSmith43");
        userDTO.setPassword("SecureP4ssw0rd319!");
        userDTO.setPrimaryEmail("steviesmith@gmail.com");
        userDTO.setSecondaryEmail("stephen.smith@sme.co.uk");
        userDTO.setRoleId(1L);
        userDTO.setMobile(7713422893L);
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post(BASE_PATH + "/create-user")
                .then()
                .statusCode(201)
                .body(containsString("User created successfully."));
    }

    @Test
    @Order(2)
    public void testLogin() {
        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post(BASE_PATH + "/login")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        userDTO.setPassword("NewPassword123!%");

        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post(BASE_PATH + "/update-user")
                .then()
                .statusCode(200)
                .body(containsString("User details updated successfully."));
    }

    @Test
    @Order(4)
    public void testLoginAfterUpdatePassword() {
        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post(BASE_PATH + "/login")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void testUpdateUserRole() {
        userDTO.setRoleId(2L);

        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .post(BASE_PATH + "/update-user-role")
                .then()
                .statusCode(200)
                .body(containsString("User details updated successfully."));
    }

    @Test
    @Order(6)
    public void testDeleteUser() {
        given()
                .contentType("application/json")
                .body(userDTO)
                .when()
                .delete(BASE_PATH + "/delete-user")
                .then()
                .statusCode(204);
    }
}
