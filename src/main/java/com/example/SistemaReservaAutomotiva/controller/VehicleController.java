package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.dto.input.RegisterVehicleDTO;
import com.example.SistemaReservaAutomotiva.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // ============= NO AUTHORIZATION CONTROLLER ================== //

    @GetMapping("/top")
    public ResponseEntity<?> getTopReservedVehicles(
            @RequestParam(name = "limit") Integer limit
    ){
        return ResponseEntity.ok(vehicleService.getTopReserved(limit));
    }

    // ================== CLIENT CONTROLLER ======================= //



    // ================= EMPLOYEE CONTROLLER ====================== //

    @PostMapping("/register")
    public ResponseEntity<?> registerVehicle(@RequestBody @Valid RegisterVehicleDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.registerVehicle(data));
    }

    // ================== ADMIN CONTROLLER ======================= //

    @GetMapping("/get")
    public ResponseEntity<?> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

}
