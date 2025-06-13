package TestPostmanMethods;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostFormData {
    private static final String BASE_URL = "https://postman-echo.com";

    @Test
    public void testPostFormData() {
        given()
                .baseUri(BASE_URL)
                .contentType("application/x-www-form-urlencoded")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")

                .when()
                .post("/post")

                .then()
                .statusCode(200)
                .contentType("application/JSON")
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }
}
