package GitHub;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import io.restassured.RestAssured;

public class JsonObjectClassDemo {

	public static void main(String[] args) {
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "Rahul Kumar");
		obj1.put("gender", "male");
		obj1.put("email", "RahulKumar@gmail.com");
		obj1.put("status", "Active");
		
		
		RestAssured.baseURI="https://gorest.co.in";
		String token = "6b4cd8f2cadf05a8bb1b4487cd23b5ca45f0eebe1ffaa860aaa26b509ede5d8a";
	      
			
			given()
			.header("Accept", "application/json")
			.header("content-Type", "application/json")
			.header("Authorization", "Bearer " + token)
			.body(obj1.toString())
						
			.when().post("/public/v2/users")
			
			.then().statusCode(201)
			.log().all();


	}

}
