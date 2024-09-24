package com.airbnb.bnb.Repository;

import com.airbnb.bnb.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}