package com.fnatlas.api.repositories;

import com.fnatlas.api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByMapCode(String mapCode);
    List<Review> findReviewsByUserId(Long userId);
    boolean existsByMapCodeAndUserId(String mapCode, Long userId);
}
