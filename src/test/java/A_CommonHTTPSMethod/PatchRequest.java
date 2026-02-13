package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatchRequest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.restful-api.dev";
		
		given()
		.contentType("application/json")
		.body("{\r\n"
				+ "	\"data\": {\r\n"
				+ "    \"year\": 2025,\r\n"
				+ "    \"CPU model\": \"Intel Core i9 Test Rahul\"\r\n"
				+ " }\r\n"
				+ "}")
		.when().patch("/objects/ff8081819782e69e019b4b37952e6248")
		.then().log().all()
		.statusCode(200)
		.body("name", equalTo("Apple MacBook Pro 16"));
		
		
	}

}

