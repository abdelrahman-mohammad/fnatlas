package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.MapResponse;
import com.fnatlas.api.entities.Review;
import com.fnatlas.api.services.FortniteApiService;
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
    private final FortniteApiService fortniteApiService;

    @GetMapping("/{mapCode}")
    public MapResponse getMapData(@PathVariable String mapCode) {
        return fortniteApiService.getMapByCode(mapCode);
    }

    @GetMapping("/{mapCode}/reviews")
    public List<Review> getMapReviews(@PathVariable String mapCode) {
        return reviewsService.getReviewsByMapCode(mapCode);
    }
}
