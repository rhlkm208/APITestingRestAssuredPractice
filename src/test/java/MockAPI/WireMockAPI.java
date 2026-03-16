package MockAPI;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WireMockAPI {

    public static void main(String[] args) {

        // Start WireMock on different port
        WireMockServer wireMockServer = new WireMockServer(9090);
        wireMockServer.start();

        // Configure WireMock
        configureFor("localhost", 9090);

        // Create Stub Mapping
        stubFor(get(urlEqualTo("/api/users/101"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\":101, \"name\":\"John\", \"role\":\"Admin\" }")));

        // Call API using Rest Assured
        given()
        .when()
            .get("http://localhost:9090/api/users/101")
        .then()
            .statusCode(200);
 //           .body("name", equalTo("John"));

        // Stop server
        wireMockServer.stop();
    }
}
