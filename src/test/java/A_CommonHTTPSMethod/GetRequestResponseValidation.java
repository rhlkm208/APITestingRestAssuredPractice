package A_CommonHTTPSMethod;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequestResponseValidation {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://api.restful-api.dev";
		given()
		.when().get("/objects")
		.then().log().all()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.contentType("application/json")
		.body("id", equalTo("12"))
		.body("name", equalTo("Apple iPad Air"))
		.body("data.Price", equalTo("419.99"))
		.body("data.Capacity", equalTo("64 GB"))
		.header("Transfer-Encoding", equalTo("chunked"))
		.time(lessThan(4000L));
	
	}

}
