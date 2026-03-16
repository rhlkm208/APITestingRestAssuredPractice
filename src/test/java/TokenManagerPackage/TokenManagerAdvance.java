package TokenManagerPackage;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TokenManagerAdvance {

    private static String accessToken;
    private static String refreshToken;
    private static long expiryTime;

    private static final String LOGIN_PATH = "/login";
    private static final String REFRESH_PATH = "/refresh";

    public static synchronized String getToken() {

        if (accessToken == null) {
            login();
        }
        else if (isExpired()) {
            refreshAccessToken();
        }

        return accessToken;
    }

    private static void login() {

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\"username\":\"" + ConfigManager.get("username") +
                                "\",\"password\":\"" + ConfigManager.get("password") + "\"}")
                .when()
                        .post(LOGIN_PATH)
                .then()
                        .statusCode(200)
                        .extract()
                        .response();

        accessToken = response.jsonPath().getString("access_token");
        refreshToken = response.jsonPath().getString("refresh_token");

        int expiresIn = response.jsonPath().getInt("expires_in");
        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L);

        System.out.println("Login Successful. Token Generated.");
    }

    private static void refreshAccessToken() {

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\"refresh_token\":\"" + refreshToken + "\"}")
                .when()
                        .post(REFRESH_PATH)
                .then()
                        .statusCode(200)
                        .extract()
                        .response();

        accessToken = response.jsonPath().getString("access_token");
        int expiresIn = response.jsonPath().getInt("expires_in");

        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L);

        System.out.println("Access Token Refreshed.");
    }

    private static boolean isExpired() {
        return System.currentTimeMillis() >= expiryTime;
    }
}
