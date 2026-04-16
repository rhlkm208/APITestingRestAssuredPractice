package B_Authentication;

import Utils.ConfigManager;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class DifferentToeknFormatHandelling {

	// Bearer
	// Authorization: Bearer token

	// API Key
	// x-api-key: token

	// Basic (via Query Parameter)
	// Authorization: Basic base64

	// Cookie
	// Cookie: access_token=token

	public static RequestSpecification getAuthenticatedRequest() {

		RequestSpecification request = given();

		String tokenType = ConfigManager.get("token_type");

		switch (tokenType.toUpperCase()) {

		case "BEARER":
			request.header("Authorization", "Bearer " + ConfigManager.get("bearer_token"));
			break;

		case "API_KEY":
			request.header("x-api-key", ConfigManager.get("api_key"));
			break;

		case "QUERY":
			request.queryParam("access_token", ConfigManager.get("query_token"));
			break;

		case "COOKIE":
			request.cookie("access_token", ConfigManager.get("cookie_token"));
			break;

		default:
			throw new RuntimeException("Unsupported token type: " + tokenType);
		}

		return request;

	}

}
