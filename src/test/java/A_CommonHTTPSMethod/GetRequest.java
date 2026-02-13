package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetRequest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://api.restful-api.dev";
		
		given()
		.when().get("/objects")
		.then().log().all().statusCode(200);
	}

}
