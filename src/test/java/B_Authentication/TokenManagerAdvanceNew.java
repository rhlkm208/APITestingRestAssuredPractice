package B_Authentication;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class TokenManagerAdvanceNew {

    private static String accessToken;
    private static String refreshToken;
    private static long expiryTime;

    public static synchronized String getToken() {

        if (accessToken == null) {
            login();
        } else if (isExpired()) {
            refreshToken();
        }
        return accessToken;
    }

    public static void login() {

        Response res =
        given()
            .contentType("application/x-www-form-urlencoded")
            .auth().preemptive().basic("client_id", "client_secret")
            .formParam("grant_type", "client_credentials")
        .when()
            .post("/oauth/token")
        .then()
            .statusCode(200)
            .extract().response();

        accessToken = res.jsonPath().getString("access_token");
        refreshToken = res.jsonPath().getString("refresh_token");
        int expiresIn = res.jsonPath().getInt("expires_in");

        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L);
    }

    public static void refreshToken() {

        Response res =
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "refresh_token")
            .formParam("refresh_token", refreshToken)
        .when()
            .post("/oauth/token")
        .then()
            .statusCode(200)
            .extract().response();

        accessToken = res.jsonPath().getString("access_token");
        int expiresIn = res.jsonPath().getInt("expires_in");

        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L);
    }

    public static boolean isExpired() {
        return System.currentTimeMillis() >= expiryTime;
    }
}