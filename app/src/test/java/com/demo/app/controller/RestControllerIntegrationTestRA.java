package com.demo.app.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerIntegrationTestRA {

    @BeforeAll
    public static void setup() {
       // RestAssured.baseURI = "http://localhost:8089";
    }

    @Test
    public void testHealth() {
        RestAssured.given().
                // baseUri("http://localhost:8080").
                        when().
                get("/api/health").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
