package com.example.diningreview.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @LocalServerPort
    int randomServerPort;

    protected RequestSpecification reqSpec;
    protected ValidatableResponse response;

    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "http://localhost";
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.setAccept(ContentType.JSON);
        requestSpec.setContentType(ContentType.JSON);
        requestSpec.setPort(randomServerPort);
        reqSpec = requestSpec.build();
    }
}
