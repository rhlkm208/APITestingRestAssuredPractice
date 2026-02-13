package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetRequestPathParam {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://jobs.postmanatwork.com/";

		given().pathParam("id", "3-VI0301EBw9s84_i0W1v")
		.when().get("/jobs/{id}")
		.then().log().all().statusCode(200);

		given()
		.when().get("/jobs/3-VI0301EBw9s84_i0W1v")
		.then().log().all().statusCode(200);

	}

}
