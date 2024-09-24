package com.airbnb.bnb.Service;

import com.airbnb.bnb.Entity.Property;

public interface PropertyService {
    public Property CreateProperty(Property property, long city_id, long country_id);
}
