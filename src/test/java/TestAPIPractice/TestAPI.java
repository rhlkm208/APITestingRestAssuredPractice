package TestAPIPractice;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestAPI {

    public RequestSpecification reqSpec;

    // -------------------------------
    // REQUEST SPECIFICATION BUILDER
    // -------------------------------
    @BeforeClass
    public void ReqSpecBuild() {

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri("https://api.restful-api.dev");
        reqBuilder.setBasePath("/objects");
        reqBuilder.setContentType("application/json");

        reqSpec = reqBuilder.build();
    }

    // -------------------------------
    // POST REQUEST
    // -------------------------------
    @Test
    public void TestPost() {

        String requestBody = "{\r\n"
                + "  \"name\": \"Rahul\",\r\n"
                + "  \"data\": {\r\n"
                + "     \"year\": 2024,\r\n"
                + "     \"price\": 10000\r\n"
                + "  }\r\n"
                + "}";

        given()
            .spec(reqSpec)
            .body(requestBody)

        .when()
            .post()

        .then()
            .statusCode(200)
            .body("name", equalTo("Rahul"))
            .log().all();
    }

    // -------------------------------
    // GET REQUEST
    // -------------------------------
    @Test
    public void TestGet() {

        Response res =
                given()
                    .spec(reqSpec)

                .when()
                    .get("/7")

                .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

        JsonPath jp = res.jsonPath();
        String name = jp.getString("name");

        Assert.assertNotNull(name);
    }

    // -------------------------------
    // GET WITH JSON SCHEMA VALIDATION
    // -------------------------------
    @Test
    public void TestGetWithJsonSchemaFile() {

        given()
            .spec(reqSpec)

        .when()
            .get("/7")

        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("productSchema.json"))
            .log().all();
    }
}
