package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.dto.input.NewPurchaseDTO;
import com.example.SistemaReservaAutomotiva.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody NewPurchaseDTO data, @RequestHeader HttpHeaders header){
        String token = header.getFirst("Authorization");

        return ResponseEntity.ok(purchaseService.createNewPurchase(data, token));
    }

}
