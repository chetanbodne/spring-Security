package com.example.exception;

public class BadExists extends RuntimeException{
    public BadExists(String msg) {
        super(msg);
    }
}
