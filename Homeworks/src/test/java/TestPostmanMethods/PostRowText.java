package TestPostmanMethods;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRowText {
    private static final String BASE_URL = "https://postman-echo.com";
    private static final String REQUEST_BODY = "{\"test\": \"value\"}";

    @Test
    public void testPostRowText() {
        given()
                .log()
                .body()
                .baseUri(BASE_URL)
                .contentType("text/plain")
                .body(REQUEST_BODY)

                .when()
                .post("/post")

                .then()
                .log()
                .body()
                .statusCode(200)
                .body("data", equalTo(REQUEST_BODY));
    }
}
