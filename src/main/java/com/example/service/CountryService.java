package com.example.service;

import com.example.entity.Country;
import com.example.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository countryRepository;


    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public Country addCountry(Country country){
        Country save = countryRepository.save(country);
        return save;

    }



    public Country findCountry(long countryid) {
        Optional<Country> byId = countryRepository.findById(countryid);
        Country country = byId.get();
        return country;
    }
}
