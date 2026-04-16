package FileUploadAndDownload;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UploadFile {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://the-internet.herokuapp.com/upload";

		String path = "C:\\Users\\rahulkumar02\\eclipse-workspace\\APITestingRestAssuredPractice\\Downloads\\OAuth 2.0 Architecture.jpg";
		File file = new File(path);
		
		Response response = RestAssured
				.given()
				.multiPart("file", file, "multipart/form-data")
				.post()
				.thenReturn();
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		
	}

}
