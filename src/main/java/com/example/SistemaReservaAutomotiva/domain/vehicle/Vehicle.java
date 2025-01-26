package com.example.SistemaReservaAutomotiva.domain.vehicle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID registry;

    private VehicleBrand brand;
    private String model;

    private Long fab_year;

    @Column(unique = true, nullable = false, name = "Plate")
    private String plate;

    private String color;

    private Long mileage;

    private Float price;
    private LocalDate registerday;

    private Disponibility disponibility;

}
