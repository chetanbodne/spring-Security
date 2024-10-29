package com.example.service;

import com.example.entity.Bad;
import com.example.payload.LoginDto;
import com.example.repository.BadRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BadService {
    private BadRepository badRepository;
private JWTService jwtService;
    public BadService(BadRepository badRepository, JWTService jwtService) {
        this.badRepository = badRepository;
        this.jwtService = jwtService;
    }

    public Bad createBad(Bad bad) {
        String hashpw = BCrypt.hashpw(bad.getPassword(), BCrypt.gensalt(10));
        bad.setPassword(hashpw);
        Bad save = badRepository.save(bad);
        return save;

    }

    public Optional<Bad> findByEmail(String email) {
        Optional<Bad> byEmail = badRepository.findByEmail(email);
        return byEmail;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<Bad> byUsername = badRepository.findByUsername(loginDto.getUsername());
        if (byUsername.isPresent()) {
            Bad bad = byUsername.get();
            // return    BCrypt.checkpw(loginDto.getPassword(),bad.getPassword());
            if (BCrypt.checkpw(loginDto.getPassword(), bad.getPassword())) ;
         return    jwtService.generateToken(bad);
        }
        return null;
    }

}
