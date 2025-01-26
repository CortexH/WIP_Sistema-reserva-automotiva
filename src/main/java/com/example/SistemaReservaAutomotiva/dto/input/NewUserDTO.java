package com.example.SistemaReservaAutomotiva.dto.input;

import com.example.SistemaReservaAutomotiva.domain.user.UserRoles;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record NewUserDTO(
        @NotNull(message = "O campo 'name' é obrigatório")
        String name,

        @NotNull(message = "O campo 'email' é obrigatório")
        String email,

        @NotNull(message = "O campo 'password' é obrigatório")
        String password,

        @NotNull(message = "O campo 'cep' é obrigatório")
        String cep,

        @NotNull(message = "O campo 'phone' é obrigatório")
        String phone,

        // remover
        UserRoles role
) {}
