package GitHub;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APIChainingOnGItHub {
	
	public static String CreatedRepoName;
	
	
	public void CreateRepo() {
		RestAssured.baseURI="https://api.github.com";
	       String token = System.getenv("GITHUB_TOKEN");
			
	       JSONObject obj = new JSONObject(); 
	       obj.put("name", "CreateRepo2");
	       
		String CreateRepoResponse =	given()
			.header("Accept", "application/vnd.github+json")
			.header("Authorization", "bearer " + token)
			.header("X-GitHub-Api-Version", "2022-11-28")
			.body(obj.toString())
			
			.when().post("/user/repos")
			
			.then().statusCode(201)
			.log().all().extract().asString();
		
		JsonPath jpath = new JsonPath(CreateRepoResponse);
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
		
		RestAssured.baseURI="https://api.github.com";
	    String token = System.getenv("GITHUB_TOKEN");
	    String ownername = "rhlkm208";
		
	       
		given()
			.header("Accept", "application/vnd.github+json")
			.header("Authorization", "bearer " + token)
			.header("X-GitHub-Api-Version", "2022-11-28")
			.pathParam("owner", ownername)
			.pathParam("repo", CreatedRepoName)
			
			.when().get("/repos/{owner}/{repo}")
			
			.then().statusCode(404)
			.log().all();
	}
	
	

	public static void main(String[] args) {
		APIChainingOnGItHub repoObj = new APIChainingOnGItHub();
		repoObj.CreateRepo();
		repoObj.DeleteRepo();
		repoObj.GetRepo();
		
		System.out.println("API Chaining Completed");
		
		
		
		

	}

}
