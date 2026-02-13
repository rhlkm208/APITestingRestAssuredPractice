package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.Assert;


public class Testing {
	
	public void main(String[] args) {
		
		RestAssured.baseURI ="https://baseurl"; 
		
		Response response = 
				given()
				.get("/api/users/2");
	//			.then().extract().response();
		JsonPath jp = response.jsonPath();
		
		String name = jp.getString("data.first_name");
		Assert.assertEquals(name, "Janet");

		
		
		given()
		.body("{ username: 'Rahul', userid : '101' }")
		.header("Authorization", "bearer", "token")
		
		.when().post("/createemployee")
		.then()
		.statusCode(201);
	}
	

}
