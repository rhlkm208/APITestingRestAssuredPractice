package B_Authentication;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class BearerToken {

	public static void main(String[] args) {
RestAssured.baseURI="https://api.openweathermap.org";
		
		given()
		.queryParam("q", "Patna")
		.queryParam("appid", "c0feb958dcfad9dfd6e769fa6eb09655")
		.header("Authorization", "bearer OAuthdeepdivescenarios")
		.when().get("/data/2.5/weather")
		.then().log().all().statusCode(200).time(lessThan(1500L))
		.body("weather[0].'id'", equalTo(721))
		.body("weather[0].'main'", equalTo("Haze"));
		

	}

}
