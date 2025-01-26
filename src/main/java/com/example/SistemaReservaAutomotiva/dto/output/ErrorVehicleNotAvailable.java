package com.example.SistemaReservaAutomotiva.dto.output;

import java.time.LocalDate;

public record ErrorVehicleNotAvailable(
        Integer status,
        String message,
        String id,
        LocalDate return_date
) {
}
