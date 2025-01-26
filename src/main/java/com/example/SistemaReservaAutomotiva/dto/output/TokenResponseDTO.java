package com.example.SistemaReservaAutomotiva.dto.output;

import lombok.Builder;

@Builder
public record TokenResponseDTO(
        Integer code,
        String token
) {
}
