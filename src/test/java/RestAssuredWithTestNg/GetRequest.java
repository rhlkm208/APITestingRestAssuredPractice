package RestAssuredWithTestNg;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetRequest {
	
	
	@Test
	public void getWeatherDetails() {
		RestAssured.baseURI="https://api.restful-api.dev";
		RestAssured.basePath="/objects/ff8081819782e69e019b66946bdf0a69";
		
		given()
		.when().get()
		.then().statusCode(200)
		.log().all();
	
	}
	

}
