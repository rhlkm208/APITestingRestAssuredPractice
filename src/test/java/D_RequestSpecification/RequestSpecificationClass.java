package D_RequestSpecification;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationClass {

	public static void main(String[] args) {
	
		RequestSpecBuilder requestbuilder = new RequestSpecBuilder();
		requestbuilder
		.setBaseUri("https://jobs.postmanatwork.com")
		.setBasePath("/jobs")
		.setContentType("application/json");
		
		RequestSpecification requestspec = requestbuilder.build();
		
// 	List of all jobs
		given()
		.spec(requestspec)
		.when().get()	
		.then().statusCode(200).log().all();
		
//		List of jobs from a particular company like Canada
		
		given()
		.spec(requestspec)
		.queryParam("country", "CA")
		.when().get()	
		.then().statusCode(200).log().all();
		

	}

}
