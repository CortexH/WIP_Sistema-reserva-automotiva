package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.dto.input.NewProductDTO;
import com.example.SistemaReservaAutomotiva.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ============= NO AUTHORIZATION CONTROLLER ================== //

    @GetMapping("/find")
    public ResponseEntity<?> findProducts(@RequestParam(name = "regId") String registerId){
        return ResponseEntity.ok(productService.findAndFormatProduct(registerId));
    }

    // ================= EMPLOYEE CONTROLLER ====================== //

    @PostMapping("/register")
    public ResponseEntity<?> registerNewProduct(@RequestBody @Valid NewProductDTO data,
                                                @RequestHeader HttpHeaders headers
    ){
        String token = headers.getFirst("Authorization");
        return ResponseEntity.ok(productService.registerNewProduct(data, token));
    }

}
