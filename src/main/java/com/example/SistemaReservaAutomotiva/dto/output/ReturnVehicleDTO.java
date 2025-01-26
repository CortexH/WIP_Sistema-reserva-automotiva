package com.example.SistemaReservaAutomotiva.dto.output;

import com.example.SistemaReservaAutomotiva.domain.vehicle.Disponibility;
import com.example.SistemaReservaAutomotiva.domain.vehicle.VehicleBrand;

public record ReturnVehicleDTO(
        String registry,
        VehicleBrand brand,
        String model,
        Long fab_year,
        String plate,
        String color,
        Long mileage,
        Float price,
        Disponibility disponibility
) {
}
