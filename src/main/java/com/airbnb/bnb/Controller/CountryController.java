package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.Country;
import com.airbnb.bnb.Service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService CountryService;
    public CountryController(CountryService countryService) {
        this.CountryService = countryService;
    }
    //http://localhost:8080/api/v1/country
    @PostMapping
    public ResponseEntity<Country> addCountry( @RequestBody Country country){
    Country c=CountryService.CreateCountry(country);
    return new ResponseEntity<>(c , HttpStatus.CREATED);
    }

    }

