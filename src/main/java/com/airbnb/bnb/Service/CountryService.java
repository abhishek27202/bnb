package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.Country;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {
    public  Country CreateCountry(Country country);

}
