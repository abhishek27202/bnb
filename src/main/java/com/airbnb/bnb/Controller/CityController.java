package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.City;
import com.airbnb.bnb.Service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private CityService CityService;
    public CityController(CityService cityService) {
        this.CityService = cityService;
    }
    //http://localhost:8080/api/v1/city
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
       City c= CityService.CreateCity(city);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
