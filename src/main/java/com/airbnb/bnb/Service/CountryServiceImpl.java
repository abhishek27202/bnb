package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.Country;
import com.airbnb.bnb.Repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public  Country CreateCountry(Country country) {
       Country saveEntity= countryRepository.save(country);
       return saveEntity;
    }


}
