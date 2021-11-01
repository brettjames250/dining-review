package com.example.diningreview.repositories;

import com.example.diningreview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    boolean existsByPostcode(String postcode);

    boolean existsByName(String name);
}
