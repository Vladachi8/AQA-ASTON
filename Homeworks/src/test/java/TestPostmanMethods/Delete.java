package TestPostmanMethods;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class Delete {
    private static final String BASE_URL = "https://postman-echo.com";
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";

    @Test
    public void testDeleteWithRawText() {
        given()
                .log()
                .body()
                .baseUri(BASE_URL)
                .contentType("text/plain")
                .body(REQUEST_BODY)

                .when()
                .delete("/delete")

                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType("application/JSON")
                .body("data", equalTo(REQUEST_BODY))
                .body("json", nullValue())
                .body("headers.'content-type'", equalTo("text/plain"))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}
