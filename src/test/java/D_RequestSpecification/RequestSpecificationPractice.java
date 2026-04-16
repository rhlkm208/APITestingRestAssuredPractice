package D_RequestSpecification;

import com.github.javafaker.File;

import B_Authentication.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationPractice {
	
	public static RequestSpecification reqspec;
	
	public static RequestSpecification getRequestSpec() {

        String token = TokenManager.getToken();
        String file = System.getProperty("user.dir") + "/file/doc.png";

        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri("https://api.example.com");

        // Correct way to set Bearer token
        builder.addHeader("Authorization", "Bearer " + token);

        // JSON Body
        builder.setBody("{\"name\":\"Rahul\",\"age\":30}");

        // File upload
        builder.addMultiPart("file", new java.io.File(file));

        reqspec = builder.build();
        
        return reqspec;
	}
}
