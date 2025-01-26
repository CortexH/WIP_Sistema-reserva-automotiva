package com.example.SistemaReservaAutomotiva.dto.input;

import com.example.SistemaReservaAutomotiva.domain.vehicle.VehicleBrand;
import jakarta.validation.constraints.NotNull;

public record RegisterVehicleDTO(
        @NotNull(message = "campo 'marca' não pode ser nulo")
        VehicleBrand brand,
        @NotNull(message = "campo 'modelo' não pode ser nulo")
        String model,
        @NotNull(message = "campo 'fabrication_year' não pode ser nulo")
        Long fabrication_year,
        @NotNull(message = "campo 'license_plate' não pode ser nulo")
        String license_plate,
        @NotNull(message = "campo 'color' não pode ser nulo")
        String color,
        @NotNull(message = "campo 'mileage' não pode ser nulo")
        Long mileage,
        @NotNull(message = "campo 'day_price' não pode ser nulo")
        Float day_price
) {
}
