package E_SerializationAndDeserialization;

import static org.testng.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import F_SerializationAndDeserializationPOJO.POJOData;
import F_SerializationAndDeserializationPOJO.POJOProductAndDataClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializationNew {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
		
		POJOProductAndDataClass product = new POJOProductAndDataClass();
		product.setName("Rahul");
		
		POJOData data = new POJOData();
		data.setCPUmodel("i15");
		data.setHarddisksize("250 TB");
		data.setPrice(5000.987);
		data.setYear(2026);
		
		product.setData(data);
		
		RestAssured.baseURI = "https://api.restful-api.dev";
		String json = MAPPER.writeValueAsString(product);
		
		Response response = 
				RestAssured.given()
				.contentType("application/json")
		.log().all()
		.body(json)
		.post("/objects").andReturn();
		
		response.then().log().all();
		
		assertEquals(response.getStatusCode(), 200, "HTTP Message");
		
	}
	
	
	


}
