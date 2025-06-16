package TestPostmanMethods;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostFormData {
    private static final String BASE_URL = "https://postman-echo.com";
    private static final String REQUEST_BODY = "{\"foo1\":\"bar1\",\"foo2\":\"bar2\"}";

    @Test
    public void testPostFormData() {
        given()
                .log()
                .body()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(REQUEST_BODY)

                .when()
                .post("/post")

                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType("application/JSON")
                .body("data.foo1", equalTo("bar1"))
                .body("data.foo2", equalTo("bar2"));
    }
}
