package com.example.webshop.util;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}