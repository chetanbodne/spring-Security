package com.example.service;

import com.example.entity.Property;
import com.example.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public Property addProperty(Property property) {
        Property save = propertyRepository.save(property);
        return save;

    }

    public List<Property> findByCity(String city) {
        List<Property> cityname = propertyRepository.findCityname(city);
        return cityname;

    }

    public Property findById(long propertyid) {
        Optional<Property> byId = propertyRepository.findById(propertyid);
        Property property = byId.get();
        return property;
    }
}
