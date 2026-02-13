package TestAPIPractice;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import java.util.UUID;



public class CompleteValidation {
	
	public static RequestSpecification reqspec;
	
	
	
	public static void main(String[] args) {
		
		String tokenType = "";
	    String accessToken ="";
		String ClientID ="";
		String ClientSecret ="";
		String userid ="";
		String pwd ="";
		

// Request Specification		
		
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder()
				.setContentType("application/json")
				.setBaseUri("")
				.setBody("");
		
		reqspec = reqbuilder.build();

//	   RestAssured.baseURI = "https://api.example.com";
//     RestAssured.basePath = "/v1";

	    given()
	    
	    //Base URI & Base Path
	    .baseUri("https://api.example.com")
	    .basePath("/v1")
	    
	    //Headers -> Content type and Authorization
	    .header("Content-Type", "application/json")
	    .header("Accept", "application/json")
	    .header("Authorization", "Bearer " + accessToken)
	    .header("X-Correlation-Id", UUID.randomUUID().toString())
	    .header("Authorization", tokenType+ " " + accessToken)
	    
	    
	    //Authentication (Real-Time Ways)
	    .auth().oauth2(accessToken) //Bearer Token / JWT
	    .auth().preemptive().basic("username", "password") //Basic Auth
	    .header("x-api-key", "abc123") //API Key
	    
	    .auth().basic(userid, pwd)
		.auth().preemptive().basic(userid, pwd)
		.auth().oauth(ClientSecret, userid, accessToken, pwd)
		.auth().oauth2(accessToken)
		.auth().preemptive().basic(ClientID, ClientSecret)
	    
	   //Query Parameters (GET heavy usage)
	    .queryParam("page", 1)
	    .queryParam("size", 10)
	    .queryParam("sort", "name")
	    .queryParams(Map.of(
	    	    "page", 1,
	    	    "size", 20
	    	))
	    
	    //Path Parameters
	    .pathParam("userId", 101)
	    
	    //Request Body (String)
	    .body("{ \"name\": \"Rahul\", \"role\": \"QA\" }")
	    
	    //Content Type Setting
	    .contentType(ContentType.JSON)
	    
	    //Logging (Debug / CI)
	    .log().all()
	    .log().ifValidationFails()





     
     
		.when().get()
		

	// Response Verification	
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.body("name", equalTo("Rahul"))
		.body("data.color", equalTo("Cloudy White"))
		.body("roles", hasSize(3)) //Array/List Validation
		.body("roles", hasItem("QA")) //Array/List Validation
		.body("id", notNullValue()) //Validate Response Is Not Null
		.body("$", not(empty())) //Validate Response Is Not Empty
		.body("$", hasKey("id")) //Key Existence Validation
		.body(matchesJsonSchemaInClasspath("userSchema.json")) //Schema Validation (Enterprise MUST)
		
		;
		
	//	User user = response.as(User.class);
	//	Assert.assertEquals(user.getName(), "Rahul");
	}
	
	

}
