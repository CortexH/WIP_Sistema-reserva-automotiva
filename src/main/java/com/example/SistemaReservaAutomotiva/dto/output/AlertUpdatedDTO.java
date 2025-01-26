package com.example.SistemaReservaAutomotiva.dto.output;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertState;

public record AlertUpdatedDTO(
        String registry,
        AlertState state,
        String message,
        String update
) {
}
