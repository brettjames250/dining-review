package com.example.diningreview.tests;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DiningReviewIT extends BaseTest {

    @Test
    public void getAllReviews() {
        response = given(reqSpec).when().get("/dining-review")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getAllPendingReview(){
        response = given(reqSpec).when().get("/dining-review/pending")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void createAndApproveDiningReview() {
        Map<String, Object> map = new HashMap<>();
        map.put("reviewedBy", "BilboBaggins");
        map.put("restaurantId", 1);
        map.put("peanutScore", 1);
        map.put("eggScore", 1);
        map.put("dairyScore", 1);
        map.put("commentary", "Comment");
        map.put("adminReviewStatus", "PENDING");

        // creating new dining-review + extracting the id
        int reviewId = given(reqSpec).body(map)
                .when().post("/dining-review")
                .then().assertThat().statusCode(200)
                .extract().path("id");

        // convert id to string
        String idStr = String.valueOf(reviewId);

        // approving the review
        response = given(reqSpec).body(map)
                .when()
                .put("/dining-review/" + idStr + "/approve")
                .then().assertThat().statusCode(200).and()
                .body("adminReviewStatus", equalTo("APPROVED"));
    }

    @Test
    public void createAndRejectDiningReview() {
        Map<String, Object> map = new HashMap<>();
        map.put("reviewedBy", "BilboBaggins");
        map.put("restaurantId", 1);
        map.put("peanutScore", 1);
        map.put("eggScore", 1);
        map.put("dairyScore", 1);
        map.put("commentary", "Comment");
        map.put("adminReviewStatus", "PENDING");

        // creating new dining-review + extracting the id
        int reviewId = given(reqSpec).body(map)
                .when().post("/dining-review")
                .then().assertThat().statusCode(200)
                .extract().path("id");

        // convert id to string
        String idStr = String.valueOf(reviewId);

        // reject the review
        response = given(reqSpec).body(map)
                .when()
                .put("/dining-review/" + idStr + "/reject")
                .then().assertThat().statusCode(200).and()
                .body("adminReviewStatus", equalTo("REJECTED"));
    }

    @Test
    public void testNonExistentRestaurant(){
        Map<String, Object> map = new HashMap<>();
        map.put("reviewedBy", "BilboBaggins");
        map.put("restaurantId", 99999999);
        map.put("peanutScore", 1);
        map.put("eggScore", 1);
        map.put("dairyScore", 1);
        map.put("commentary", "Comment");
        map.put("adminReviewStatus", "PENDING");

        // verify bad request http status code returned
        response = given(reqSpec).body(map)
                .when().post("/dining-review")
                .then().assertThat().statusCode(400);
    }

    @Test
    public void testNonExistentUser(){
        Map<String, Object> map = new HashMap<>();
        map.put("reviewedBy", "NonExistentUser");
        map.put("restaurantId", 1);
        map.put("peanutScore", 1);
        map.put("eggScore", 1);
        map.put("dairyScore", 1);
        map.put("commentary", "Comment");
        map.put("adminReviewStatus", "PENDING");

        // verify bad request http status code returned
        response = given(reqSpec).body(map)
                .when().post("/dining-review")
                .then().assertThat().statusCode(400);
    }



}
