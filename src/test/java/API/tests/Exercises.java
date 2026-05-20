package API.tests;

import API.config.TestConfig;
import API.data.TestData;
import API.utils.utilsToken;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Exercises  extends BaseTest {

    @Test
    public void shouldGetSingleProduct() {

        given()
                .when()
                .get(TestConfig.fakestoreapi_base_url +
                        TestConfig.products_endpoint + "/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyString()))
                .body("price", greaterThan(0F));
    }

    @Test
    public void shouldGetAllProducts() {

        given()
                .when()
                .get(TestConfig.fakestoreapi_base_url +
                        TestConfig.products_endpoint)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("title", everyItem(notNullValue()))
                .body("category", everyItem(notNullValue()))
                .body("image", everyItem(notNullValue()));
    }

    @Test
    public void shouldCreateProduct() {

        String title = "Tema10";
        String category = "API testing";

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("price", 777.77);
        payload.put("description",
                "Your perfect pack for everyday use.");
        payload.put("image",
                "https://fakestoreapi.com/img/test.png");
        payload.put("category", category);

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(TestConfig.fakestoreapi_base_url +
                        TestConfig.products_endpoint)
                .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("category", equalTo(category));
    }

    @Test
    public void shouldLimitProducts() {

        given()
                .queryParam("limit", 5)
                .when()
                .get(TestConfig.dummyjson_base_url +
                        TestConfig.products_endpoint)
                .then()
                .statusCode(200)
                .body("limit", equalTo(5))
                .body("products.size()", equalTo(5));
    }

    @Test
    public void shouldSearchProducts() {

        given()
                .queryParam("q", "phone")
                .when()
                .get(TestConfig.dummyjson_base_url +
                        TestConfig.search_endpoint)
                .then()
                .statusCode(200)
                .body("products.size()", greaterThan(0))
                .body("products[0].title", notNullValue())
                .body("products[0].price", notNullValue());
    }

    @Test
    public void shouldSendCustomHeader() {

        given()
                .header("Course-Name", "API Automation")
                .when()
                .get(TestConfig.httpbin_base_url +
                        TestConfig.headers_endpoint)
                .then()
                .statusCode(200)
                .body("headers.Course-Name",
                        equalTo("API Automation"));
    }

    @Test
    public void shouldAuthenticateWithBasicAuth() {

        given()
                .auth()
                .basic("user", "passwd")
                .when()
                .get(TestConfig.httpbin_base_url +
                        "/basic-auth/user/passwd")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

    @Test
    public void shouldGenerateToken() {

        Map<String, String> payload = new HashMap<>();
        payload.put("username", "emilys");
        payload.put("password", "emilyspass");

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(TestConfig.dummyjson_base_url +
                                "/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("accessToken");

        System.out.println("Token: " + token);
    }

    @Test
    public void shouldGetCurrentUser() {

        String token =
                utilsToken.getToken(
                        TestData.username,
                        TestData.password
                );

        given()
                .header("Authorization",
                        "Bearer " + token)
                .when()
                .get(TestConfig.dummyjson_base_url +
                        "/auth/me")
                .then()
                .statusCode(200)
                .body("username", notNullValue())
                .body("email", notNullValue());
    }
}