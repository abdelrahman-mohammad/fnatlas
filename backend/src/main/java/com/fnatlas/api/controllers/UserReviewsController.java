package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.ReviewRequest;
import com.fnatlas.api.entities.Review;
import com.fnatlas.api.services.ReviewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/reviews")
public class UserReviewsController {

    private final ReviewsService reviewsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@PathVariable Long userId, @RequestBody @Valid ReviewRequest reviewRequest) {
        return reviewsService.createReview(reviewRequest, userId);
    }

    @GetMapping
    public List<Review> getUserReviews(@PathVariable Long userId) {
        return reviewsService.getReviewsByUserId(userId);
    }

    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable Long userId, @PathVariable Long reviewId) {
        return reviewsService.getReviewByIdAndUserId(reviewId, userId);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody @Valid ReviewRequest reviewUpdatesRequest) {
        return reviewsService.updateReview(reviewId, userId, reviewUpdatesRequest);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long userId, @PathVariable Long reviewId) {
        reviewsService.deleteReview(reviewId, userId);
    }
}
