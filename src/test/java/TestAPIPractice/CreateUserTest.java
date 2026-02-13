package TestAPIPractice;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class CreateUserTest {

    @Test
    public void createUser() {

        given()
                .header("Authorization", "Bearer " + APITokenManagerClass.getToken())
                .body("{ \"name\": \"Rahul\" }")
        .when()
                .post("https://example.com/api/users")
        .then()
                .statusCode(201);
    }
}
