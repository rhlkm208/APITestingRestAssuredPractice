package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PutRequest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.restful-api.dev";
		
		given().contentType("application/json")
		.body("{\r\n"
				+ "   \"name\": \"Rahul Apple MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2019,\r\n"
				+ "      \"price\": 2849.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}")
		.when().put("/objects/ff8081819782e69e019b4b37952e6248")
		.then().log().all()
		.statusCode(200)
		.body("data.price", equalTo(2849.99F))
		.time(lessThan(2000L));

	}

}
