package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> findByPlate(String plate);
    Boolean existsByPlate(String plate);

    @Query(value = "SELECT v.*, COUNT(r.vehicle_id) AS total " +
            "FROM vehicles v " +
            "JOIN reservations r ON r.vehicle_id = v.registry " +
            "WHERE r.status = 'COMPLETED'" +
            "GROUP BY v.registry " +
            "ORDER BY total DESC " +
            "LIMIT :limit", nativeQuery = true
    )
    List<Vehicle> findTopMostReservedNative(@Param("limit") Integer limit);

}
