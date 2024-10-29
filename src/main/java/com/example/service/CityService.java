package com.example.service;

import com.example.entity.City;
import com.example.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
private CityRepository cityRepository;


    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public City createCity(City city){
        City save = cityRepository.save(city);
        return save;
    }

    public City findCity(long cityid) {
        Optional<City> byId = cityRepository.findById(cityid);
        City city = byId.get();

        return city;
    }
}
