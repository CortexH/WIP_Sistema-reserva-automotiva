package com.example.SistemaReservaAutomotiva.controller;

import com.example.SistemaReservaAutomotiva.dto.input.LoginUserDTO;
import com.example.SistemaReservaAutomotiva.dto.input.NewUserDTO;
import com.example.SistemaReservaAutomotiva.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// only for user controller

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // =============== NO AUTHORIZATION CONTROLLER ================= //

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid NewUserDTO data){
        return ResponseEntity.ok(this.userService.createNewUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginUserDTO data){
        return ResponseEntity.ok(userService.loginUser(data));
    }

    // ================== CLIENT CONTROLLER ======================= //

    @PostMapping("/logout")
    public ResponseEntity<?> userLogout(){
        return ResponseEntity.ok("logout");
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(){
        return ResponseEntity.ok("changedpassword");
    }

    // ================== ADMIN CONTROLLER ======================= //

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
