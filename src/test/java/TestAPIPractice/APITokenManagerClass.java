package TestAPIPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITokenManagerClass {

    private static String token;

    // Method to get token
    public static String getToken() {

        // If token already generated, reuse it
        if (token == null) {
            generateToken();
        }

        return token;
    }

    // Generate token method
    private static void generateToken() {

        String username = System.getenv("API_USERNAME");
        String password = System.getenv("API_PASSWORD");

        Response response =
                RestAssured
                        .given()
                        .contentType("application/json")
                        .body("{\n" +
                                "  \"username\": \"" + username + "\",\n" +
                                "  \"password\": \"" + password + "\"\n" +
                                "}")
                        .post("https://example.com/api/login");

        token = response.jsonPath().getString("token");

        System.out.println("Generated Token: " + token);
    }
}

