package com.fnatlas.api.controllers;

import com.fnatlas.api.entities.Review;
import com.fnatlas.api.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maps")
public class MapsController {

    private final ReviewsService reviewsService;

    @GetMapping("/{mapCode}/reviews")
    public List<Review> getReviewsByMapCode(@PathVariable String mapCode) {
        return reviewsService.getReviewsByMapCode(mapCode);
    }

    @GetMapping("/{mapCode}/reviews/{reviewId}")
    public Review getMapReview(@PathVariable String mapCode, @PathVariable Long reviewId) {
        return reviewsService.getReviewByIdAndMapCode(reviewId, mapCode);
    }
}
