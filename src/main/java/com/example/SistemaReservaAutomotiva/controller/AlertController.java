package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertState;
import com.example.SistemaReservaAutomotiva.dto.input.SendAlertDTO;
import com.example.SistemaReservaAutomotiva.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // ================== CLIENT CONTROLLER ======================= //

    @GetMapping("/get")
    public ResponseEntity<?> getUserAlertOnlyByToken(@RequestHeader HttpHeaders headers){
        String token = headers.getFirst("Authorization");

        return ResponseEntity.status(HttpStatus.OK).body(alertService.getUserAlertsByToken(token, null, null));
    }

    @PatchMapping("/updstate")
    public ResponseEntity<?> updateStateOfAlert(
            @RequestBody(required = true) SendAlertDTO data,
            @RequestHeader HttpHeaders header
            ){
        return ResponseEntity.ok(alertService.changeAlertState(data, header.getFirst("Authorization")));
    }

    // ================= EMPLOYEE CONTROLLER ====================== //

    @GetMapping("/getall")
    public ResponseEntity<?> getUserAlerts(
            @RequestHeader HttpHeaders headers,
            @RequestParam(name = "userId", required = false) Long id,
            @RequestParam(name = "state", required = false) AlertState state
    ){
        String token = headers.getFirst("Authorization");

        return ResponseEntity.status(HttpStatus.OK).body(alertService.getUserAlertsByToken(token, state, id));
    }

}
