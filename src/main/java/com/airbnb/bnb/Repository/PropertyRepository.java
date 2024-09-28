package com.airbnb.bnb.Repository;

import com.airbnb.bnb.Entity.AppUser;
import com.airbnb.bnb.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findAllById(long id);
    @Query(value = "select p from Property p JOIN City c ON p.city = c.id where c.name =:cityName ")
   // @Query("select p from Property p JOIN p.city c  where c.name = :cityName")
    List<Property> searchProperty(
           @Param("cityName") String cityName

    );
}