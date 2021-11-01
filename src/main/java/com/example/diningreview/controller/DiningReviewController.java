package com.example.diningreview.controller;

import com.example.diningreview.model.DiningReview;
import com.example.diningreview.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiningReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping
    public DiningReview createNewDiningreview(@RequestBody DiningReview diningReview){
        return reviewRepository.save(diningReview);
    }

    @GetMapping
    public Iterable<DiningReview> getAllDiningReviews(){
        return reviewRepository.findAll();
    }
}
