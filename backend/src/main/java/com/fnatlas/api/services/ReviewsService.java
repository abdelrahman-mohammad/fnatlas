package com.fnatlas.api.services;

import com.fnatlas.api.dtos.review.ReviewCreateRequest;
import com.fnatlas.api.dtos.review.ReviewUpdateRequest;
import com.fnatlas.api.entities.Review;
import com.fnatlas.api.entities.User;
import com.fnatlas.api.exceptions.EntityNotFoundException;
import com.fnatlas.api.repositories.ReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final UserService userService;

    public Review createReview(ReviewCreateRequest reviewCreateRequest, Long userId) {
        User user = userService.getUserById(userId);

        if (reviewsRepository.existsByMapCodeAndUserId(reviewCreateRequest.getMapCode(), userId))
            throw new IllegalArgumentException("User has already reviewed this map");

        Review review = Review.builder()
                .mapCode(reviewCreateRequest.getMapCode())
                .user(user)
                .rating(reviewCreateRequest.getRating())
                .content(reviewCreateRequest.getContent())
                .build();

        return reviewsRepository.save(review);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        userService.getUserById(userId);
        return reviewsRepository.findReviewsByUserId(userId);
    }

    public Review getReviewByIdAndUserId(Long reviewId, Long userId) {
        userService.getUserById(userId);
        return reviewsRepository.findReviewByIdAndUserId(reviewId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Review", reviewId));
    }

    public Review updateReview(Long reviewId, Long userId, ReviewUpdateRequest reviewUpdatesRequest) {
        Review existingReview = getReviewByIdAndUserId(reviewId, userId);

        if (reviewUpdatesRequest.getRating() != 0) existingReview.setRating(reviewUpdatesRequest.getRating());
        if (reviewUpdatesRequest.getContent() != null) existingReview.setContent(reviewUpdatesRequest.getContent());

        return reviewsRepository.save(existingReview);
    }

    public void deleteReview(Long reviewId, Long userId) {
        getReviewByIdAndUserId(reviewId, userId);
        reviewsRepository.deleteById(reviewId);
    }

    // Map-related methods

    public List<Review> getReviewsByMapCode(String mapCode) {
        return reviewsRepository.findReviewsByMapCode(mapCode);
    }

}
