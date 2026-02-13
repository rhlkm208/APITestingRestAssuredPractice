package E_SerializationAndDeserialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import F_SerializationAndDeserializationPOJO.POJOData;
import F_SerializationAndDeserializationPOJO.POJOProductAndDataClass;

public class ProductAndDataClass {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.restful-api.dev";
		
		POJOProductAndDataClass product = new POJOProductAndDataClass();
		product.setName("Rahul Kumar Tester");
		
		
		POJOData data = new POJOData();
		data.setCPUmodel("i10");
		data.setHarddisksize("25 TB");
		data.setPrice(2000.987);
		data.setYear(2019);
		
		product.setData(data);
		
		
		given()
		.contentType("application/json")
		.body(product)
		
		.when()
		.post("/objects")
		
		.then().log().all();

	}

}
