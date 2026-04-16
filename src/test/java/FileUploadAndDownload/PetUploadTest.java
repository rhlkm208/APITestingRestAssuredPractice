package FileUploadAndDownload;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetUploadTest {

    public void petUploadImage() {

        int id = 123;

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2/pet/{id}/uploadImage";

//        File file = new File("C:\\Users\\rahulkumar02\\eclipse-workspace\\APITestingRestAssuredPractice\\First Floor.jpeg");
        File file = new File(System.getProperty("user.dir")+File.separator+"First Floor.jpeg");

        Response res =
                given()
                        .pathParam("id", id)
                        .header("accept", "application/json")
                        .multiPart("file", file)
                .when()
                        .post()
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .extract()
                        .response();

        System.out.println(res.asPrettyString());
    }
    
    public static void main(String[] args) {
    	
    	PetUploadTest pu = new PetUploadTest();
    	pu.petUploadImage();
    	
    }
}