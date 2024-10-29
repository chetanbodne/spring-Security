package com.example.controller;

import com.example.entity.City;
import com.example.entity.Country;
import com.example.entity.Property;
import com.example.service.CityService;
import com.example.service.CountryService;
import com.example.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    private CityService cityService;
    private CountryService countryService;

    public PropertyController(PropertyService propertyService, CityService cityService, CountryService countryService) {
        this.propertyService = propertyService;
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property, @RequestParam long cityid, @RequestParam long countryid) {
        City city = cityService.findCity(cityid);
        Country country = countryService.findCountry(countryid);
        property.setCity(city);
        property.setCountry(country);
        Property property1 = propertyService.addProperty(property);
        return new ResponseEntity<>(property1, HttpStatus.CREATED);


    }
    @GetMapping("/searchProperty")
    public ResponseEntity<List<Property>>findByCity(@RequestParam String city){
        List<Property> byCity = propertyService.findByCity(city);
        return new ResponseEntity<>(byCity,HttpStatus.OK);

    }
}
