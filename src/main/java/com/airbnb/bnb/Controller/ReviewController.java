package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Entity.Review;
import com.airbnb.bnb.Repository.PropertyRepository;
import com.airbnb.bnb.Repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Review> createReview(
            @RequestBody Review review,
    @AuthenticationPrincipal AppUser user,
        @RequestParam long propertyId
    ){
        Property property=propertyRepository.findAllById(propertyId).get();
        review.setAppUser(user);
        review.setProperty(property);
       Review savedReview= reviewRepository.save(review);
        return  new ResponseEntity<>(savedReview , HttpStatus.CREATED);
    }
}
