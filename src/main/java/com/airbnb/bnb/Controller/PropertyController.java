package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Repository.PropertyRepository;
import com.airbnb.bnb.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;
    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property,
    @RequestParam long city_id, @RequestParam long country_id) {
      Property p=  propertyService.CreateProperty(property,city_id,country_id);
      return new ResponseEntity<>(p, HttpStatus.CREATED);

    }

    @GetMapping("/propertyresult")
    public List<Property> SearchProperty(
            @RequestParam("city") String CityName
    ){
       return  propertyRepository.searchProperty(CityName);
    }
}
