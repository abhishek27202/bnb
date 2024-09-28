package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.City;
import com.airbnb.bnb.Entity.Country;
import com.airbnb.bnb.Entity.Property;
import com.airbnb.bnb.Repository.CityRepository;
import com.airbnb.bnb.Repository.CountryRepository;
import com.airbnb.bnb.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{

private PropertyRepository propertyRepository;
private CityRepository cityRepository;
private CountryRepository countryRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Property CreateProperty(Property property, long city_id, long country_id) {
        City city = cityRepository.findById(city_id).get();
        Country country = countryRepository.findById(country_id).get();
        property.setCity(city);
        property.setCountry(country);
        Property saveEntity=  propertyRepository.save(property);
        return saveEntity;
    }
}
