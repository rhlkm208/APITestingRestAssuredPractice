package FileUploadAndDownload;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DownloadFile {

	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI="https://reqres.in/api/users";
		
		
		Response response = RestAssured
				.given()
				.header("x-api-key", "pro_f16c94c4de759779a327afbf37c558d513fa53487afcabcb05f5c44d9189dc51")
				.when().get()
				.andReturn();
		
		byte[] bytes = response.getBody().asByteArray();
		String path = "C:\\Users\\rahulkumar02\\eclipse-workspace\\APITestingRestAssuredPractice\\Downloads\\jsonfile.json";
		File file = new File(path);
		Files.write(bytes, file);
		
		System.out.println("File Downloaded Successfully");

	}

}
