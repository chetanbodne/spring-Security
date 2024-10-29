package com.example.repository;

import com.example.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p JOIN p.city c where c.name=:name")
    List<Property>findCityname(@Param("name")String cityName);


}