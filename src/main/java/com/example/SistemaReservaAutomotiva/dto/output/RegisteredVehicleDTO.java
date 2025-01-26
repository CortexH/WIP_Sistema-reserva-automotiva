package com.example.SistemaReservaAutomotiva.dto.output;

import lombok.Builder;

@Builder
public record RegisteredVehicleDTO(
        String registry
) {
}
