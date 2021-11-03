package com.example.diningreview.repositories;

import com.example.diningreview.model.AdminReviewStatus;
import com.example.diningreview.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<DiningReview, Long> {
    Iterable<DiningReview> findByIdAndAdminReviewStatus(Long id, AdminReviewStatus status);
    Iterable<DiningReview> findByAdminReviewStatus(AdminReviewStatus pending);
}
