package com.example.SistemaReservaAutomotiva.dto.output;

import java.time.LocalDateTime;

public record ReservationCreatedDTO(
        String ticket,
        LocalDateTime collect_time
) {
}
