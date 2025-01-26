package com.example.SistemaReservaAutomotiva.dto.input;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertType;
import com.example.SistemaReservaAutomotiva.domain.user.User;

import java.util.HashMap;

public record NewAlertDTO(
        User user,
        String message,
        AlertType type
) {
}
