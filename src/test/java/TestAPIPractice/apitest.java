package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



public class apitest {

	public static void main(String[] args) {
		
		 
		RestAssured.baseURI = "";
		String tokenType = "";
		String accessToken ="";
		String ClientID ="";
		String ClientSecret ="";
		String userid ="";
		String pwd ="";
		
// Request Specification 		
		RequestSpecBuilder reqSpecbuild = new RequestSpecBuilder();
		reqSpecbuild
		.setBaseUri("")
		.setBasePath("")
		.setContentType("");
		
		RequestSpecification reqspec = reqSpecbuild.build();
		
		RestAssured.baseURI = "https://api.example.com";
        RestAssured.basePath = "/v1";
				
		given()
		
// Passing Body		
		.body("{\r\n"
				+ "   \"name\": \"Rahul MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2025,\r\n"
				+ "      \"price\": 1849.99,\r\n"
				+ "      \"CPU model\": \"Rahul Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}")
// Passing ContentType		
		.header("Content-Type", "application/json")
		.header("Accept", "Application/json" )
		.contentType("application/json")

// Passing Path Parameter		
		.pathParam("id", "3-VI0301EBw9s84_i0W1v")
		
// Passing Query Parameter
		.queryParams("country", "US", "location", "remote")
		.queryParam("q", "Delhi")

		
		.header("appid", "c0feb958dcfad9dfd6e769fa6eb09655")
		.queryParam("appid", "c0feb958dcfad9dfd6e769fa6eb09655")
        .param("grant_type", "client_credentials")
        
        .header("Authorization", "Bearer 6b4cd8f2cadf05a8bb1b4487cd23b5ca45f0eebe1ffaa860aaa26b509ede5d8a")		
        .header("Authorization", tokenType+ " " + accessToken)
        .auth().preemptive().basic(ClientID, ClientSecret)
        
        
        .auth().basic(userid, pwd)
		.auth().preemptive().basic(userid, pwd)
		.auth().oauth(ClientSecret, userid, accessToken, pwd)
		.auth().oauth2(accessToken)
        
        .spec(reqspec)
           
              
        .baseUri("https://api.example.com")
        .basePath("/v1")
        
        
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
