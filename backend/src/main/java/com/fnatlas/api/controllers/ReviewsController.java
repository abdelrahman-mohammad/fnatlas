package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.review.ReviewCreateRequest;
import com.fnatlas.api.dtos.review.ReviewUpdateRequest;
import com.fnatlas.api.entities.Review;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import com.fnatlas.api.services.AuthService;
import com.fnatlas.api.services.FortniteApiService;
import com.fnatlas.api.services.ReviewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final AuthService authService;
    private final FortniteApiService fortniteApiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@PathVariable Long userId, @RequestBody @Valid ReviewCreateRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        if(!fortniteApiService.mapExists(request.getMapCode()))
            throw new EntityNotFoundException("Fortnite Map", "map code", request.getMapCode());
        return reviewsService.createReview(request, userId);
    }

    @GetMapping
    public List<Review> getUserReviews(@PathVariable Long userId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return reviewsService.getReviewsByUserId(userId);
    }

    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable Long userId, @PathVariable Long reviewId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return reviewsService.getReviewByIdAndUserId(reviewId, userId);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody @Valid ReviewUpdateRequest request, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        return reviewsService.updateReview(reviewId, userId, request);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long userId, @PathVariable Long reviewId, @RequestHeader(value = "Authorization") String token) {
        authService.verifyAuthorization(userId, token);
        reviewsService.deleteReview(reviewId, userId);
    }
}
