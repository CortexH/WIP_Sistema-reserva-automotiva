package com.example.SistemaReservaAutomotiva.dto.input;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertState;

public record SendAlertDTO(
        String registry,
        AlertState new_state
) {
}
