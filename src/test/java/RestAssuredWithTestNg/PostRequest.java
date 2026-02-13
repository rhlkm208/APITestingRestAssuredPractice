package RestAssuredWithTestNg;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PostRequest {

//	public static HashMap map = new HashMap();

	@BeforeClass
	public void postdata() {

		RestAssured.baseURI = "https://api.restful-api.dev";
		RestAssured.basePath = "/objects";

	}

	@Test
	public void testPost() {

		given().contentType("application/json")
				.body("{\r\n" + "   \"name\": \"Rahul Kumar MacBook Pro 16\",\r\n" + "   \"data\": {\r\n"
						+ "      \"year\": 2025,\r\n" + "      \"price\": 211849.99,\r\n"
						+ "      \"CPU model\": \"Intel Core i9\",\r\n" + "      \"Hard disk size\": \"1 TB\"\r\n"
						+ "   }\r\n" + "}")

				.when().post()

				.then().statusCode(200).body("name", equalTo("Rahul Kumar MacBook Pro 16")).log().all();
	}

}
