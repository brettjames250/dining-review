package com.example.diningreview.controller;

import com.example.diningreview.model.Restaurant;
import com.example.diningreview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public Iterable<Restaurant> getAllRestaurants(){

        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Optional<Restaurant> getRestaurantBy(@PathVariable("id") Long id ){
        return restaurantRepository.findById(id);
    }

    @GetMapping("/restaurants/{idd}")
    public Optional<Restaurant> getRestaurantByPostcodeAndAtLeastOneReview(@RequestParam String postc ){
        return null;
    }

    @PostMapping("/restaurants")
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant){
        if(restaurantRepository.existsByPostcode(restaurant.getPostcode()) && restaurantRepository.existsByName(restaurant.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A restaurant with this name and postcode already exists");
        }
        return restaurantRepository.save(restaurant);
    }
}
