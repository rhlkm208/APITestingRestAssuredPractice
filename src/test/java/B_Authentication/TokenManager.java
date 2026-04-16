package B_Authentication;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class TokenManager {
	
    private static String token;
    private static long expiryTime;

    public static String getToken() {

        if (token == null || isExpired()) {
            generateToken();
        }

        return token;
    }

    private static void generateToken() {
    	

        Response res = given()
                .header("Content-Type", "application/json")
                .body("{\"username\":\"admin\",\"password\":\"admin123\"}")
                .post("https://baseurl/login");

        token = res.jsonPath().getString("access_token");
        int expiresIn = res.jsonPath().getInt("expires_in");

        expiryTime = System.currentTimeMillis() + (expiresIn * 1000L);
    }

    private static boolean isExpired() {
        return System.currentTimeMillis() >= expiryTime;
    }
}
