package com.example.service;

import com.example.entity.Bad;
import com.example.entity.Property;
import com.example.entity.Review;
import com.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public Review addReviews(Review review) {
        Review save = reviewRepository.save(review);
        return save;

    }

    public Review findByBadAndProperty(Bad bad, Property property) {
        Review byBadAndProperty = reviewRepository.findByBadAndProperty(bad, property);
        return byBadAndProperty;

    }

    public List<Review> findReviewByBad(Bad bad) {
        List<Review> reviewByBad = reviewRepository.findReviewByBad(bad);
        return reviewByBad;

    }
}
