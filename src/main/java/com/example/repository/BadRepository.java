package com.example.repository;

import com.example.entity.Bad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadRepository extends JpaRepository<Bad, Long> {
    Optional<Bad> findByEmail(String email);

    Optional<Bad> findByUsername(String username);
}