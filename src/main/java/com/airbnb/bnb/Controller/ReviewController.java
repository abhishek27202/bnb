package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Entity.Review;
import com.airbnb.bnb.Repository.PropertyRepository;
import com.airbnb.bnb.Repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    private PropertyRepository propertyRepository;
    @RequestMapping("/createReview")
    public ResponseEntity<?> createReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId
    ) {
        Property property = propertyRepository.findAllById(propertyId).get();
        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);
        if (reviewDetails!=null){
            // Return the response if the review already exists
           return new ResponseEntity<>("Review Exists", HttpStatus.CREATED);
        }

// Proceed with saving the new review if it doesn't exist
        review.setAppUser(user);
        review.setProperty(property);
        Review savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
    @GetMapping("/usersReviews")
    public List<Review> listReviewsOfUser(
            @AuthenticationPrincipal AppUser user
    ){
        List<Review> reviewsByUser = reviewRepository.findReviewsByUser(user);
        return reviewsByUser;
    }
}