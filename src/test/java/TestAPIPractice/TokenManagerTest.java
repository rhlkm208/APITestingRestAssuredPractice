package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManagerTest {
	private static String accessToken;
	private static String refreshToken;
	private static long expiryTime;

	private static final String BASE_URL ="";
	private static final String loginPath = "/login";
	private static final String refreshPath = "/refreshlogin";

	public static synchronized String getAccessToken() {

		if (accessToken == null) {
			login();
		} else if (isTokenExpired()) {
			refreshAccessToken();
		}
		return accessToken;
	}

	private static void login() {
		RestAssured.baseURI = BASE_URL;

		Response res = given().contentType("application/json").auth().preemptive().basic("client_User", "client_Access")
				.when().post(loginPath).then().statusCode(200).extract().response();

		accessToken = res.jsonPath().getString("access_token");
		refreshToken = res.jsonPath().getString("refresh_token");
		int expireIn = res.jsonPath().getInt("expires_in");
		expiryTime = System.currentTimeMillis() + (expireIn * 1000L);

	}

	public static void refreshAccessToken() {

		RestAssured.baseURI = BASE_URL;

		Response res = given().contentType("application/json").auth().oauth2(refreshToken).when().post(refreshPath)
				.then().statusCode(201).extract().response();

		accessToken = res.jsonPath().getString("access_token");
		int expireIn = res.jsonPath().getInt("expires_in");
		expiryTime = System.currentTimeMillis() + (expireIn * 1000L);
	}

	public static boolean isTokenExpired() {
		return System.currentTimeMillis() >= expiryTime;
	}

}
