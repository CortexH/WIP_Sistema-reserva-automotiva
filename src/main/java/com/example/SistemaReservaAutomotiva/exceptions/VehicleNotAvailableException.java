package com.example.SistemaReservaAutomotiva.exceptions;

public class VehicleNotAvailableException extends RuntimeException {
    public VehicleNotAvailableException(String message) {
        super(message);
    }
}
