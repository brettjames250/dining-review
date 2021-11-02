package com.example.diningreview.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends BaseTest {

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
