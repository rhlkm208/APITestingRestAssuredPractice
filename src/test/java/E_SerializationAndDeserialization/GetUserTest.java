package E_SerializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import F_SerializationAndDeserializationPOJO.POJOGetUserTest;

import static io.restassured.RestAssured.given;

public class GetUserTest {

    @Test
    public void getUserAndValidateName() {

        RestAssured.baseURI = "https://api.example.com";

        // Step 1: Send request and store response
        Response response =
                given()
                    .log().all()
                .when()
                    .get("/users/101")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();

        // Step 2: Convert JSON response to User POJO
        POJOGetUserTest user = response.as(POJOGetUserTest.class);

        // Step 3: Validate data
        Assert.assertEquals(user.getName(), "Rahul");
    }
}

