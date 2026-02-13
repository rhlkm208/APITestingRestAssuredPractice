package C_JSON_AND_SchemaValidation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicValidationJSON {
	
	@Test
	public void testStatusCodeAndLog() {
		
		given()
		
		.when()
		.get("https://api.restful-api.dev/objects")
		
		.then()
		.statusCode(200)
		.log().all()
		;
	}

	@Test
	public void testSingleContent() {
		
		given()
		.when().get("https://api.restful-api.dev/objects/3")
		.then()
		.statusCode(200)
		.body("data.color", equalTo("Cloudy White"))
		;
	}
	
	@Test
	public void testMultipleContent() {
		
		given()
		.when().get("https://api.restful-api.dev/objects")
		.then()
		.statusCode(200)
		.body("data.color", hasItems("Cloudy White","Cloudy White","Purple", "Brown", "Red"))
		;
	}
}
