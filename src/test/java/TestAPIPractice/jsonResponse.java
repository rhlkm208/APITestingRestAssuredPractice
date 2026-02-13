package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.List;

public class jsonResponse {
	
	public static RequestSpecification reqspec;

	public static void main(String[] args) {
				
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder()
		.setBaseUri("")
		.setBody("")
		.setContentType("application/json");
		
		 reqspec = reqbuilder.build();		
		
		
		RestAssured.baseURI="";
		
	Response res =	given()
		.when().get("")
		.then().statusCode(200).extract().response();

	JsonPath jp = res.jsonPath();
	String username = jp.getString("data.users[0].username");
	System.out.println(username);
	
	List<String> emails = jp.getList("data.users.email");
	
	for(String email : emails) {
		System.out.println(email);
	}
	
	String bname = jp.getString("store.inventory.books[0].title");
	System.out.println(bname);
	
	String bookcost = jp.getString("store.inventory.books[0].price");
	System.out.println(bookcost);
	
	List<String> booknames = jp.getList("store.inventory.books.title");
	
	for(String bookname : booknames) {
		System.out.println(bookname);
	}
	}

}
