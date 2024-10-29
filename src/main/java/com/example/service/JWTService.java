package com.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.Bad;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;
    private Algorithm algorithm;
private static final String USER_NAME="username";
    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(Bad bad) {
        return JWT.create()
                .withClaim(USER_NAME, bad.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
public String getUsername(String token){
    DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
 return    decodedJWT.getClaim(USER_NAME).asString();

}
}
