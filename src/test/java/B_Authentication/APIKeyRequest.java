package B_Authentication;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIKeyRequest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.openweathermap.org";
		
		given()
		.queryParam("q", "Delhi")
		.queryParam("appid", "c0feb958dcfad9dfd6e769fa6eb09655")
//		.header("appid", "c0feb958dcfad9dfd6e769fa6eb09655")
		
        .when().get("/data/2.5/weather")
         
		.then().log().all().statusCode(200).time(lessThan(1500L))
		.body("weather[0].'id'", equalTo(701))
		.body("weather[0].'main'", equalTo("Mist"));
		

	}

}
