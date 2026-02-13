package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;




public class ResponseMethod {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		
	Response res =	given()
		.when().get("/users")
		.then().statusCode(200)
		.log().all()
		.body("phone[0]", equalTo("1-770-736-8031 x56442"))
		.body("company[3].name", equalTo("Robel-Corkery"))
		.extract().response()
		;

	JsonPath jp = res.jsonPath();
	String value = jp.getString("company[3].name");
	System.out.println(value);
	System.out.println(jp.getString("company[9].name"));
	
	
	}

}
