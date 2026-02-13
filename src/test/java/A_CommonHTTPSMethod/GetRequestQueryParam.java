package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetRequestQueryParam {

	public static void main(String[] args) {
		RestAssured.baseURI="https://jobs.postmanatwork.com/";
		given().queryParams("country", "US", "location", "remote")
		.when().get("/jobs")
		.then().log().all().statusCode(200);

	}

}
