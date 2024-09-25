package com.airbnb.bnb.Repository;

import com.airbnb.bnb.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}