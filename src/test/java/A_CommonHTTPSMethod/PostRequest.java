package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://api.restful-api.dev";
		
		given().contentType("application/json")
		.body("{\r\n"
				+ "   \"name\": \"Rahul Kumar MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2025,\r\n"
				+ "      \"price\": 211849.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("/objects")
		.then().log().all()
		.statusCode(200)
		.body("name", equalTo("Rahul Kumar MacBook Pro 16"));
		
	}

}
