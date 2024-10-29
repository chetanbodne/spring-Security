package com.example.controller;

import com.example.entity.Bad;
import com.example.entity.Property;
import com.example.entity.Review;
import com.example.service.PropertyService;
import com.example.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private ReviewService reviewService;
private PropertyService propertyService;
    public ReviewController(ReviewService reviewService, PropertyService propertyService) {
        this.reviewService = reviewService;
        this.propertyService = propertyService;
    }
    @RequestMapping("/userReview")
    public ResponseEntity<?>createReview(@RequestBody Review review , @AuthenticationPrincipal Bad bad, @RequestParam long propertyid){
        Property property = propertyService.findById(propertyid);
        Review byBadAndProperty = reviewService.findByBadAndProperty(bad, property);

        if (byBadAndProperty!=null){
            return new ResponseEntity<>("Review Exists",HttpStatus.CREATED);

        }


        review.setBad(bad);
        review.setProperty(property);
        Review review1 = reviewService.addReviews(review);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);

    }
@GetMapping("/listReview")
    public List<Review >listOfBadReview(@AuthenticationPrincipal Bad bad){
        List<Review> reviewByBad = reviewService.findReviewByBad(bad);
        return reviewByBad;

    }

}
