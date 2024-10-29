package com.example.repository;

import com.example.entity.Bad;
import com.example.entity.Property;
import com.example.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r .property=:property and r.bad=:bad")
    Review findByBadAndProperty(@Param("bad")Bad bad, @Param("property")Property property);
    @Query("select r from Review r where r.bad=:bad")
List<Review>findReviewByBad(@Param("bad")Bad bad);

}