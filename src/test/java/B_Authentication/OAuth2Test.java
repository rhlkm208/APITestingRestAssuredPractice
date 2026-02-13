package B_Authentication;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth2Test {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api-m.sandbox.paypal.com";
		String ClientID = "AUuz210mqH9MALpyzmOzNjbxbwXevYNTiJlFRNNGB47Omh7Ae0hMtVjD7ZJKZvtt-1TdD9O4r5_8fDIU";
		String ClientSecret = "EFn1DZZ-B0k42vFWmF1n3g9d5o0BIUjhOwax2gyUFNnQJdi-pQXPNCp4IELAAAiGlKQuN9jxmtPxJlNx";

		String ResponseString = 
				given()
		          .auth().preemptive().basic(ClientID, ClientSecret)
		          .param("grant_type", "client_credentials")

			   .when().post("/v1/oauth2/token")

			   .then().statusCode(200).log().all().extract().asString();
		
		JsonPath jpath = new JsonPath(ResponseString);
		String accessToken = jpath.getString("access_token");
		String tokenType = jpath.getString("token_type");
		
		System.out.println("Access Token - " + accessToken);
		System.out.println("Token Type - " + tokenType);
		
		  
//		API for the Invoice List
		given()
		.contentType("application/json")
		.header("Authorization", tokenType+ " " + accessToken)
		.when().get("v1/invoicing/invoices")
		.then().statusCode(200).log().all();

	}

}
