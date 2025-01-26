package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.reservation.Reservation;
import com.example.SistemaReservaAutomotiva.domain.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query(value =
            "SELECT r.* FROM reservations r "+
                    "WHERE r.status = :status"
            , nativeQuery = true)
    List<Reservation> findFromStatusNative(@Param(value = "status") String status);

}
