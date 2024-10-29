package com.example.controller;

import com.example.entity.Bad;
import com.example.exception.BadExists;
import com.example.payload.JWTToken;
import com.example.payload.LoginDto;
import com.example.service.BadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bad")
public class BadController {
    private BadService badService;


    public BadController(BadService badService) {
        this.badService = badService;
    }

    @PostMapping("/createuser")
    public ResponseEntity<Bad> createBad(@RequestBody Bad bad) {
        bad.setRole("ROLE_USER");
        Bad bad1 = badService.createBad(bad);
        return new ResponseEntity<>(bad1, HttpStatus.CREATED);

    }

    @PostMapping("/createpropertyowner")
    public ResponseEntity<Bad> createPropertyOwner(@RequestBody Bad bad) {
        bad.setRole("ROLE_OWNER");
        Bad bad1 = badService.createBad(bad);
        return new ResponseEntity<>(bad1, HttpStatus.CREATED);

    }

    @PostMapping("/createpropertymanager")
    public ResponseEntity<Bad> createPropertyManager(@RequestBody Bad bad) {
        bad.setRole("ROLE_ADMIN");
        Bad bad1 = badService.createBad(bad);
        return new ResponseEntity<>(bad1, HttpStatus.CREATED);

    }
    @GetMapping("/email")
    public ResponseEntity<Bad> findByEmail(@RequestParam String email) {
        Optional<Bad> opema = badService.findByEmail(email);
        if (opema.isEmpty()) {
            throw new BadExists("email does not exists");
        }
        return new ResponseEntity<>(opema.get(), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto) {
        String token = badService.verifyLogin(loginDto);
        JWTToken jwtToken= new JWTToken();
        if (token!=null) {
            jwtToken.setTokenType("JWT");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }

}
