package com.example.diningreview.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTest {

    @LocalServerPort
    int randomServerPort;

    private ValidatableResponse response;
    private RequestSpecification reqSpec;

    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "http://localhost";
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.setAccept(ContentType.JSON);
        requestSpec.setContentType(ContentType.JSON);
        requestSpec.setPort(randomServerPort);
        reqSpec = requestSpec.build();
    }

    @Test
    public void getAllUsersTest() {
        response = given(reqSpec).when().get( "/users")
                .then().statusCode(200);
    }

    @Test
    public void getUserByUserNameTest() {
        response = given(reqSpec).when().get( "/users/BigJock")
                .then().statusCode(200)
                .assertThat().body("userName", equalTo("BigJock"));
    }

    @Test
    public void addNewUserTest(){
        Faker faker = new Faker();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", faker.name().username());
        map.put("city", faker.address().city());
        map.put("state", faker.address().state());
        map.put("postCode", faker.address().zipCode());
        map.put("isPeanutAllergy", false);
        map.put("isEggAllergy", false);
        map.put("isDairyAllergy", false);

        response = given(reqSpec).body(map)
                .when().log().all()
                .post("/users")
                .then().log().all().assertThat().statusCode(200);
    }

}
