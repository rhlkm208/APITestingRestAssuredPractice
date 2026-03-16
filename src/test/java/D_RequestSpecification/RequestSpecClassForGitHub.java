package D_RequestSpecification;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


public class RequestSpecClassForGitHub {
	
public static String CreatedRepoName;
public static RequestSpecification reqspec;
public static String ownername = "rhlkmr208";
	
		
	public void requestSpecMehod() {
		String token = System.getenv("GITHUB_TOKEN");
	
		reqspec = new RequestSpecBuilder()
	.setBaseUri("https://api.github.com")
	.addHeader("Accept", "application/vnd.github+json")
	.addHeader("Authorization", "bearer " + token)
	.addHeader("X-GitHub-Api-Version", "2022-11-28")
	.build();
	
	
	}
	
	public void CreateRepo() {
				
	       JSONObject obj = new JSONObject(); 
	       obj.put("name", "CreateRepo5");
	       
		String response =	given()
			.spec(reqspec)
			.body(obj.toString())

			
			.when().post("/user/repos")
			
			.then().statusCode(201)
			.extract().asString();
		
		JsonPath jpath = new JsonPath(response);
		CreatedRepoName = jpath.getString("name");
		System.out.println(CreatedRepoName);
	}
	
	
	  public void DeleteRepo() {
	  
		  RestAssured.baseURI="https://api.github.com";
		    String token = System.getenv("GITHUB_TOKEN");
		    String ownername = "rhlkm208";
			
		       
			given()
				.header("Accept", "application/vnd.github+json")
				.header("Authorization", "bearer " + token)
				.header("X-GitHub-Api-Version", "2022-11-28")
				.pathParam("owner", ownername)
				.pathParam("repo", CreatedRepoName)
				
				.when().delete("/repos/{owner}/{repo}")
				
				.then().statusCode(204)
				.log().all();
		}
	 
	
public void GetRepo() {
		      
		given()
		    .spec(reqspec)
		    .pathParam("repo", CreatedRepoName)
		    .pathParam("owner", ownername)
			
			.when().get("/repos/{owner}/{repo}")
			
			.then()
			.log().all().statusCode(404);
	}
	
	

	public static void main(String[] args) {
		RequestSpecClassForGitHub repoObj = new RequestSpecClassForGitHub();
		repoObj.requestSpecMehod();
		repoObj.CreateRepo();
		repoObj.DeleteRepo();
		repoObj.GetRepo();
		
		System.out.println("API Chaining Completed");
		
		
		
		

	}


}
