package com.fnatlas.api.services;

import com.fnatlas.api.dtos.ReviewRequest;
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

    public Review createReview(ReviewRequest reviewRequest, Long userId) {
        User user = userService.getUserById(userId);

        if (reviewsRepository.existsByMapCodeAndUserId(reviewRequest.getMapCode(), userId))
            throw new IllegalArgumentException("User has already reviewed this map");


        Review review = new Review(reviewRequest.getMapCode(), user, reviewRequest.getRating(), reviewRequest.getContent());

        return reviewsRepository.save(review);
    }

    public Review getReviewById(Long id) {
        return reviewsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review", id));
    }

    public List<Review> getReviewsByMapCode(String mapCode) {
        // Validate that the mapCode is not null or empty - blank until map controller is implemented
        return reviewsRepository.findReviewsByMapCode(mapCode);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        userService.getUserById(userId);
        return reviewsRepository.findReviewsByUserId(userId);
    }

    public Review updateReview(Long id, ReviewRequest reviewUpdatesRequest) {
        Review existingReview = reviewsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review", id));

        if (reviewUpdatesRequest.getRating() != 0) existingReview.setRating(reviewUpdatesRequest.getRating());
        if (reviewUpdatesRequest.getContent() != null) existingReview.setContent(reviewUpdatesRequest.getContent());

        return reviewsRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        if (!reviewsRepository.existsById(id))
            throw new EntityNotFoundException("Review", id);

        reviewsRepository.deleteById(id);
    }
}
