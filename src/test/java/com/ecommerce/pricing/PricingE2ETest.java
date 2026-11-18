package com.ecommerce.pricing;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricingE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Test 1: request at 10:00 on day 14 for product 35455 and brand 1")
    void test1() {
        given()
                .queryParam("brandId", 1)
                .queryParam("productId", 35455)
                .queryParam("applicationDate", "2020-06-14-10.00.00")
                .when()
                .get("/api/prices")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(1))
                .body("finalPrice", equalTo(35.50f));
    }

    @Test
    @DisplayName("Test 2: request at 16:00 on day 14 for product 35455 and brand 1")
    void test2() {
        given()
                .queryParam("brandId", 1)
                .queryParam("productId", 35455)
                .queryParam("applicationDate", "2020-06-14-16.00.00")
                .when()
                .get("/api/prices")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(2))
                .body("finalPrice", equalTo(25.45f));
    }

    @Test
    @DisplayName("Test 3: request at 21:00 on day 14 for product 35455 and brand 1")
    void test3() {
        given()
                .queryParam("brandId", 1)
                .queryParam("productId", 35455)
                .queryParam("applicationDate", "2020-06-14-21.00.00")
                .when()
                .get("/api/prices")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(1))
                .body("finalPrice", equalTo(35.50f));
    }

    @Test
    @DisplayName("Test 4: request at 10:00 on day 15 for product 35455 and brand 1")
    void test4() {
        given()
                .queryParam("brandId", 1)
                .queryParam("productId", 35455)
                .queryParam("applicationDate", "2020-06-15-10.00.00")
                .when()
                .get("/api/prices")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(3))
                .body("finalPrice", equalTo(30.50f));
    }

    @Test
    @DisplayName("Test 5: request at 21:00 on day 16 for product 35455 and brand 1")
    void test5() {
        given()
                .queryParam("brandId", 1)
                .queryParam("productId", 35455)
                .queryParam("applicationDate", "2020-06-16-21.00.00")
                .when()
                .get("/api/prices")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("productId", equalTo(35455))
                .body("brandId", equalTo(1))
                .body("priceList", equalTo(4))
                .body("finalPrice", equalTo(38.95f));
    }
}
