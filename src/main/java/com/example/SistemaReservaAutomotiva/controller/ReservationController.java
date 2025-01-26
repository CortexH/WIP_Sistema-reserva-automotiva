package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.dto.input.NewReservationDTO;
import com.example.SistemaReservaAutomotiva.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/new")
    public ResponseEntity<?> createReservation(
            @RequestBody @Valid NewReservationDTO data,
            @RequestHeader HttpHeaders header
    ){
        String token = header.getFirst("Authorization");
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.newReservation(data, token));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelReservation(
            @RequestHeader HttpHeaders headers,
            @RequestParam(name = "reservation") String reservation
    ){
        String token = headers.getFirst("Authorization");

        return ResponseEntity.ok(reservationService.cancelReservation(token, reservation));

    }

}
