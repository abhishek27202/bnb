package com.airbnb.bnb.Repository;

import com.airbnb.bnb.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}