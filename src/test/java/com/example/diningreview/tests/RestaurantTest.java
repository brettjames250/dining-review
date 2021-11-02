package com.example.diningreview.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestaurantTest extends BaseTest {

    @Test
    public void getAllRestaurantsTest() {
        response = given(reqSpec).when().get( "/restaurants")
                .then().statusCode(200);
    }

    @Test
    public void getRestaurantByNameTest() {
        response = given(reqSpec).when().log().all().get( "/restaurants/1")
                .then().log().all().statusCode(200)
                .assertThat().body("name", equalTo("Sergios"));
    }

    @Test
    public void addNewRestaurantTest(){
        Faker faker = new Faker();
        Map<String, Object> map = new HashMap<>();
        map.put("name", faker.hipster().word());
        map.put("postcode", faker.address().zipCode());
        map.put("cuisine", faker.food().ingredient());

        response = given(reqSpec).body(map)
                .when().log().all()
                .post("/restaurants")
                .then().log().all().assertThat().statusCode(200);
    }
}
