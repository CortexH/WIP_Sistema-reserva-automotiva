package com.example.SistemaReservaAutomotiva.dto.input;

import java.time.LocalDateTime;

public record NewReservationDTO(
        String registry,
        LocalDateTime collect_date,
        LocalDateTime return_date
) {
}
