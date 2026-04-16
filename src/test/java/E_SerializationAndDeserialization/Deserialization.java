package E_SerializationAndDeserialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import F_SerializationAndDeserializationPOJO.POJODeserialization;

public class Deserialization {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://jobs.postmanatwork.com";

   //Desearialization Example		

		POJODeserialization[] pojoClassData = 
				given()
				.when().get("/jobs")
				.then().statusCode(200)
				.log().all().extract()
				.as(POJODeserialization[].class);

		System.out.println(pojoClassData.length);
		System.out.println(pojoClassData[23].getCountry());
		System.out.println(pojoClassData[23].getLocation());

	}

}
