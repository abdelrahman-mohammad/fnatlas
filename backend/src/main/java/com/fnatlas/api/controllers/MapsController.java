package com.fnatlas.api.controllers;

import com.fnatlas.api.dtos.map.MapMetricsResponse;
import com.fnatlas.api.dtos.map.MapResponse;
import com.fnatlas.api.entities.Review;
import com.fnatlas.api.services.FortniteApiService;
import com.fnatlas.api.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{mapCode}/metrics/{interval}")
    public MapMetricsResponse getMapMetrics(
            @PathVariable String mapCode,
            @PathVariable String interval,
            @RequestParam(required = false) List<String> metrics,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        return fortniteApiService.getMapMetrics(mapCode, interval, metrics, from, to);
    }

    @GetMapping("/{mapCode}/reviews")
    public List<Review> getMapReviews(@PathVariable String mapCode) {
        return reviewsService.getReviewsByMapCode(mapCode);
    }
}
