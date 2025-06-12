package TestPostmanMethods;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get {
    private static final String BASE_URL = "https://postman-echo.com";

    @Test
    public void testGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get(BASE_URL + "/cookies")
                .then()
                .statusCode(200);
    }
}
