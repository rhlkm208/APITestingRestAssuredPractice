package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RequestValidation {

	public static RequestSpecification reqspec;
	
	public static String token = "";
	public static String user ="";

	public void RequestSpec() {
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder()
				.setContentType("application/json")
				.setBaseUri("")
				.setBody("");
		
		reqspec = reqbuilder.build();

		RestAssured.baseURI = " ";

		given()
		.body(reqspec)
		.when().get("/jobs")
		.then()
		.statusCode(200)
		.time(lessThan(2000L))
		.body("name", equalTo("Rahul")).body("data.color", equalTo("Cloudy White"));
	}

	
	public void getApi() {
		given()
		.baseUri("https://api.example.com")
		.header("Accept", "application/json")
		.auth().oauth2(token)
		.queryParam("active", true)
		.pathParam("id", 101)
		.log().all();

	}
	
	public void postApi() {
	User user = new User("Rahul", "QA");

	given()
	.baseUri("https://api.example.com")
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer " + token)
	.body(user)
	.log().all();

	}
	
	public void putApi() {
		User updatedUser = new User("Rahul Updated", "QA");

		given()
		.baseUri("https://api.example.com")
		.contentType(ContentType.JSON)
		.auth().oauth2(token)
		.pathParam("id", 101)
		.body(updatedUser)
		.log().all();

	}
	
	public void patchApi() {
		
		Map<String, Object> patchBody = new HashMap<>();
		patchBody.put("name", "Rahul Patched");

		given()
		.baseUri("https://api.example.com")
		.contentType(ContentType.JSON)
		.auth().oauth2(token)
		.pathParam("id", 101)
		.body(patchBody)
		.log().all();

		
	}
	
	public void DeleteApi() {
		
		given()
		.baseUri("https://api.example.com")
		.auth().oauth2(token)
		.pathParam("id", 101)
		.log().all();

	}
	
	
	//REQUEST SPECIFICATION (REAL FRAMEWORK USAGE)
	public void requestSpec() {
	RequestSpecification requestSpec =
			new RequestSpecBuilder()
			.setBaseUri("https://api.example.com")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer " + token)
			.log(LogDetail.ALL)
			.build();
	        
	
	        given()
	        .spec(requestSpec)
	        .body(user);

	
	}
	
	public static void main(String[] args) {

	}

}
