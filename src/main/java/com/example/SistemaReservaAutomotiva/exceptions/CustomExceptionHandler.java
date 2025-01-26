package com.example.SistemaReservaAutomotiva.exceptions;

import com.example.SistemaReservaAutomotiva.dto.output.GenericErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadRequestCustomException.class)
    public ResponseEntity<?> handleBadRequestCustomException(BadRequestCustomException e){
        GenericErrorDTO err = new GenericErrorDTO(LocalDateTime.now().toString(), 400, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(VehicleNotAvailableException.class)
    public ResponseEntity<?> handleVehicleNotAvailableException(VehicleNotAvailableException e){
        GenericErrorDTO err = new GenericErrorDTO(
                LocalDateTime.now().toString(),
                HttpStatus.CONFLICT.value(),
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(NotFound404Exception.class)
    public ResponseEntity<?> handleNotFoundException(NotFound404Exception e){
        GenericErrorDTO err = new GenericErrorDTO(
                LocalDateTime.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
