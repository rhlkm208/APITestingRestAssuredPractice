package E_SerializationAndDeserialization;

import F_SerializationAndDeserializationPOJO.POJOProductAndDataClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeserializationNew {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://api.restful-api.dev";

//		POJOProductAndDataClass product = new POJOProductAndDataClass();
		
		Response response = 
				RestAssured.given().when().get("/objects/ff8081819d150699019d34a618f33c66");
		response.then().statusCode(200);
		
		POJOProductAndDataClass res =
				response.as(POJOProductAndDataClass.class);
	            
		System.out.println(res.getName());
		System.out.println(res.getData());
	
	}

}
