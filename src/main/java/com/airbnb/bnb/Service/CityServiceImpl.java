package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.City;
import com.airbnb.bnb.Repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{
    private CityRepository cityRepository;
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City CreateCity(City city) {
     City saveEntity= cityRepository.save(city);
        return saveEntity;
    }
}
