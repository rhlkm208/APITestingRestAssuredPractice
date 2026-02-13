package G_ResponseSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class responseSpecification {
	public static ResponseSpecification responseSpec;
	
	public void responseSpec() {
		ResponseSpecBuilder resSpec = new ResponseSpecBuilder()
		.expectContentType("application/json")
		.expectResponseTime(Matchers.lessThan(1500L))
		.expectStatusCode(200);
		
		responseSpec = resSpec.build();
		
		
	}
	

	public static void main(String[] args) {
		
		RestAssured.baseURI="";
		
		given()
		.queryParam("country", "India")
		.pathParam("id", "jeuuiriei38445959543nnddnwi34")
		
		.when().get()
		
		.then()
		.spec(responseSpec)
		.log().all()
		
		;

	}

}
