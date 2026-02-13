package C_JSON_AND_SchemaValidation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.List;

public class GetResponseJsonPathValidation {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.restful-api.dev";
		
		
		  String res = given() 
				       .when().get("/objects") 
				       .then().log().all().extract().asString();
		 
		JsonPath jpath = new JsonPath(res);
		System.out.println(jpath.getString("id[1]"));
		System.out.println(jpath.getString("name[3]"));
		System.out.println(jpath.getString("data.price[3]"));
		System.out.println(jpath.getString("data.color[3]"));
		
// Recommended -- because this will return in the Object format that can be easily readable		
		List<Object> namelist = jpath.getList("name");	
	    
// Not recommended -- because this will return in the string format which will not readable
		List <String> namelist1 = jpath.getList("name");
		
		System.out.println(namelist);
		System.out.println(namelist1);
		

	}

}
