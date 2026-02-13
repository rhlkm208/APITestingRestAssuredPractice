package C_JSON_AND_SchemaValidation;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class SchemaValidationClass {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://api.restful-api.dev";
		
		given()
		.when().get("/objects/7")
		
		.then()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("productSchema.json"))
		.log().all();
		

	}

}
