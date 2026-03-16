package GitHub;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class DeleteGitHubRepo {

	public static void main(String[] args) {
		RestAssured.baseURI="https://api.github.com";
	    String token = System.getenv("GITHUB_TOKEN");
	    String ownername = "rhlkm208";
	    String CreatedRepoName = "TEST-REPONew";
		
	       
		given()
			.header("Authorization", "Bearer " + token)
			.pathParam("owner", ownername)
			.pathParam("repo", CreatedRepoName)
			
			.when().delete("/repos/{owner}/{repo}")
			
			.then().statusCode(204)
			.log().all();

	}

}
