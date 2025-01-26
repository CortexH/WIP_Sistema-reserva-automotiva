package com.example.SistemaReservaAutomotiva.exceptions;

public class BadRequestCustomException extends RuntimeException {
    public BadRequestCustomException(String message) {
        super(message);
    }
}
