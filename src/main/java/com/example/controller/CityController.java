package com.example.controller;

import com.example.entity.City;
import com.example.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @PostMapping
    public ResponseEntity<City>createCity(@RequestBody City city){
        City city1 = cityService.createCity(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);

    }

}
