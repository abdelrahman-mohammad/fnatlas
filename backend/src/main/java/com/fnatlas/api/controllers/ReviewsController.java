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
@RequestMapping("/api/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@RequestBody @Valid ReviewRequest reviewRequest, @RequestParam Long userId) {
        return reviewsService.createReview(reviewRequest, userId);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewsService.getReviewById(id);
    }

    @GetMapping("/map/{mapCode}")
    public List<Review> getReviewsByMapCode(@PathVariable String mapCode) {
        return reviewsService.getReviewsByMapCode(mapCode);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathVariable Long userId) {
        return reviewsService.getReviewsByUserId(userId);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody ReviewRequest reviewUpdatesRequest) {
        return reviewsService.updateReview(id, reviewUpdatesRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewsService.deleteReview(id);
    }
}
