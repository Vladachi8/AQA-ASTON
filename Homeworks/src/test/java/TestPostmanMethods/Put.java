package TestPostmanMethods;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Put {
    private static final String BASE_URL = "https://postman-echo.com";
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body";

    @Test
    public void testPut() {
        given()
                .log()
                .body()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(REQUEST_BODY)

                .when()
                .put("/put")

                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType("application/JSON") // Ответ в JSON
                .body("data", equalTo(REQUEST_BODY))  // Проверяем, что data == отправленный текст
                .body("headers.'content-type'", equalTo("application/json")); // Проверяем заголовок
    }
}
