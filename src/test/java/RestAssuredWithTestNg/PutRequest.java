package RestAssuredWithTestNg;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class PutRequest {
	
	
	public static HashMap map = new HashMap();
	
	String name = RestUtils.name();
	String dataYear = RestUtils.dataYear();
	String dataPrice = RestUtils.dataPrice();
	String id = "ff8081819782e69e019b6698a7fb0a6a";
	String DataYear = "data.year";
	
	@BeforeClass
	public void putData() {
		
		map.put("name", name);
		map.put("DataYear", dataYear);
		map.put("data[price]", dataPrice);
		
		RestAssured.baseURI="https://api.restful-api.dev";
		RestAssured.basePath="/objects/"+id;
		
	}

	@Test
	public void testPUT() {
		given()
		.contentType("application/json")
		.body(map)
		.when()
		.put()
		.then()
		.statusCode(200)
		.log().all();
		;
	}
}
