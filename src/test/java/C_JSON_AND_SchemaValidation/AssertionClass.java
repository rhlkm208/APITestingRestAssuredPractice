package C_JSON_AND_SchemaValidation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;

public class AssertionClass {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		
	Response res =	given()
		.when()
		   .get("/users")
		   
		.then()
		   .statusCode(200)
		   .log().all()
		   .extract().response();


	JsonPath jp = res.jsonPath();
	
	String str = jp.getString("name");
	Assert.assertTrue(str.contains("Leanne Graham"));
	
	List<String> names = jp.getList("name");
	Assert.assertTrue(names.contains("Leanne Graham"));
	
	}

}
