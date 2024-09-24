package com.airbnb.bnb.Controller;

import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property,
    @RequestParam long city_id, @RequestParam long country_id) {
      Property p=  propertyService.CreateProperty(property,city_id,country_id);
      return new ResponseEntity<>(p, HttpStatus.CREATED);

    }
}
