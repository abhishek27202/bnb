package com.airbnb.bnb.Repository;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.property=:property and r.appUser=:user")
    Review findByUserAndProperty(
           @RequestParam("user") AppUser user,
           @RequestParam("property") Property property);
    @Query("select r from Review r where r.appUser=:user")
    List<Review> findReviewsByUser(
            @Param("user") AppUser user
    );
}