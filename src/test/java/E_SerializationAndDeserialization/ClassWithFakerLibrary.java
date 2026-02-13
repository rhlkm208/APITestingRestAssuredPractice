package E_SerializationAndDeserialization;

import com.github.javafaker.Faker;

import F_SerializationAndDeserializationPOJO.POJOClassWithFakerLibrary;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class ClassWithFakerLibrary {
	
	public static void main(String[] args) {
	
	Faker faker = new Faker();

	POJOClassWithFakerLibrary data = new POJOClassWithFakerLibrary();
	
	data.setName(faker.name().fullName());
	data.setEmail(faker.internet().emailAddress());
	data.setGender("male");
	data.setStatus("active");
	
	RestAssured.baseURI="https://gorest.co.in/";
	
	given()
	.header("Content-Type", "application/json")
	.header("Accept", "Application/json" )
	.header("Authorization", "Bearer 6b4cd8f2cadf05a8bb1b4487cd23b5ca45f0eebe1ffaa860aaa26b509ede5d8a")
	.body(data)
	
	.when().post("public/v2/users")
	
	.then().statusCode(201).log().all();
	;
	
	
	}	

}
