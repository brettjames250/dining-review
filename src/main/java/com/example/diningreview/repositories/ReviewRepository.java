package com.example.diningreview.repositories;

import com.example.diningreview.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<DiningReview, Long> {
}
