package com.demo.app.controller;

import com.demo.app.model.Employee;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
public class RestControllerRAIntegrationTest {

 //   @BeforeAll
    public static void setup() {

       // RestAssured.baseURI = "http://localhost:8089";
    }

//    @Test
    public void testHealth() {
        RestAssured.given().
                        when().
                get("/api/health").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

 //   @Test
    public void testcreateEmployee() {
        RestAssured.with().body(new Employee("Anghsuman",40,"12-22-1980"))
                .when()
                .request("POST", "/api/employees")
                .then().
                log().all()
                .statusCode(201);
    }
 //   @Test
    public void testGetEmployees() {
        RestAssured.with().body(new Employee("Anghsuman",40,"12-22-1980"))
                .when()
                .request("POST", "/api/employees")
                .then().statusCode(201);

        RestAssured.when().get("/api/employees")
                .then().log().status().statusCode(200);

    }



}
