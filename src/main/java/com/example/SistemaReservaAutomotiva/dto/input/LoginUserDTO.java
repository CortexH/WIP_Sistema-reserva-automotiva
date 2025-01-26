package com.example.SistemaReservaAutomotiva.dto.input;

import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
        @NotNull(message = "O campo 'email' é obrigatório")
        String email,
        @NotNull(message = "O campo 'password' é obrigatório")
        String password
) {
}
