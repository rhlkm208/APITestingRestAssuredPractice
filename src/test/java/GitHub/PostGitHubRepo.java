package GitHub;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.RestAssured;

public class PostGitHubRepo {

	public static void main(String[] args) {
		
       RestAssured.baseURI="https://api.github.com";
       String token = System.getenv("GITHUB_TOKEN");
		
		given()
		.header("Accept", "application/vnd.github+json")
		.header("Authorization", "bearer " + token)
		.header("X-GitHub-Api-Version", "2022-11-28")
		.body("{\"name\":\"TEST-REPONew1\","
				+ "\"description\":\"This is your first repo!\","
				+ "\"homepage\":\"https://github.com\","
				+ "\"private\":false,"
				+ "\"is_template\":true}")
		
		.when().post("/user/repos")
		
		.then().statusCode(201)
		.time(lessThan(4000L))
		.body("name", equalTo("TEST-REPONew1"))
		.log().all();
	}

}
