package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EndToEndFlowAPITesting {

	public static void main(String[] args) {
		RestAssured.baseURI="https://api.restful-api.dev";
		
	String CreatedProduct =	given().contentType("application/json")
		.body("{\r\n"
				+ "   \"name\": \"Rahul MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2025,\r\n"
				+ "      \"price\": 1849.99,\r\n"
				+ "      \"CPU model\": \"Rahul Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("/objects")
		.then().log().all().extract().response().asString();
	
	JsonPath jpath = new JsonPath(CreatedProduct);
	String productId = jpath.getString("id");
	System.out.println(productId);
	
	
	given()
	.when().get("/objects/"+productId+"")
	.then()
	.statusCode(200)
	.body("id", equalTo(productId))
	.log().all();
	
	System.out.println("POST and GET Verified");
	
		
	
	given()
	.contentType("application/json")
	.body("{\r\n"
			+ "   \"name\": \"PUT Method Rahul Apple MacBook Pro 16\",\r\n"
			+ "   \"data\": {\r\n"
			+ "      \"year\": 2026,\r\n"
			+ "      \"price\": 2026.99,\r\n"
			+ "      \"CPU model\": \"PUT Method Rahul Intel Core i9\",\r\n"
			+ "      \"Hard disk size\": \"10 TB\",\r\n"
			+ "      \"color\": \"Gold\"\r\n"
			+ "   }\r\n"
			+ "}")
	.when().put("/objects/"+productId+"")
	.then()
	.statusCode(200)
	.body("name", equalTo("PUT Method Rahul Apple MacBook Pro 16"))
	.body("data.year", equalTo(2026))
	.body("data['CPU model']", equalTo("PUT Method Rahul Intel Core i9"))
	.log().all();
	
	System.out.println("PUT Method Verified");
	
	
	given()
	.when().delete("/objects/"+productId+"")
	.then()
	.statusCode(200)
	.log().all();
	
	System.out.println("DELETE Method Verified");
	
	given()
	.when().get("/objects/"+productId+"")
	.then()
	.statusCode(404)
	.log().all();
	
	System.out.println("ProductId does not exist as this got deleted");
	
	}

}
