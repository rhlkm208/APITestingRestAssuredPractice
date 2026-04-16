package B_Authentication;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PreemptiveAuthentication {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="http://postman-echo.com";
		String response=
				given()
				.auth().preemptive().basic("postman", "password")
				.when().get("basic-auth")
				.then()
				.assertThat()
				.statusCode(200)
				.time(lessThan(2000L))
//				.log().all()
				.extract().response().asString();
		
		System.out.println(response);
		
	}
	
	

}
