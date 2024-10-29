package com.example.payload;

import lombok.Data;

@Data
public class JWTToken {
    private String tokenType;
    private String token;
}
