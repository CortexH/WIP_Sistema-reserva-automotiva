package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.alert.AlertType;
import com.example.SistemaReservaAutomotiva.domain.reservation.Reservation;
import com.example.SistemaReservaAutomotiva.domain.reservation.ReservationStatus;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.domain.vehicle.Disponibility;
import com.example.SistemaReservaAutomotiva.domain.vehicle.Vehicle;
import com.example.SistemaReservaAutomotiva.dto.input.NewAlertDTO;
import com.example.SistemaReservaAutomotiva.dto.input.NewReservationDTO;
import com.example.SistemaReservaAutomotiva.dto.output.CancelReservationDTO;
import com.example.SistemaReservaAutomotiva.dto.output.GetReservationDTO;
import com.example.SistemaReservaAutomotiva.dto.output.ReservationCreatedDTO;
import com.example.SistemaReservaAutomotiva.exceptions.BadRequestCustomException;
import com.example.SistemaReservaAutomotiva.exceptions.NotFound404Exception;
import com.example.SistemaReservaAutomotiva.exceptions.VehicleNotAvailableException;
import com.example.SistemaReservaAutomotiva.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlertService alertService;

    private Float getTotalReservationValue(LocalDate retire_date, LocalDate return_date, Float reserve_value){
        Long days = ChronoUnit.DAYS.between(retire_date, return_date);
        Float tax = 1.08F;

        return days * reserve_value * tax;

    }

    public ReservationCreatedDTO newReservation(NewReservationDTO data, String token){
        Vehicle vehicle = vehicleService.getById(data.registry());

        if(vehicle.getDisponibility() != Disponibility.AVAILABLE) throw new VehicleNotAvailableException("A");

        User user = userService.getUserByToken(token);

        Reservation reservation = Reservation.builder()
                .reservation_date(data.collect_date())
                .return_date(data.return_date())
                .total_value(getTotalReservationValue(data.collect_date().toLocalDate(), data.return_date().toLocalDate(), vehicle.getPrice()))
                .user(user)
                .vehicle(vehicle)
                .status(ReservationStatus.PENDING)
                .build();

        Reservation saved_reserv = reservationRepository.save(reservation);

        LocalDate cDate = reservation.getReservation_date().toLocalDate();
        LocalTime cTime = reservation.getReservation_date().toLocalTime();

        String dd = cDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String tt = cTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));

        alertService.createAlert(new NewAlertDTO(
                user, "Your reservation has been approved! you can collect your vehicle at " + dd + ", " + tt, AlertType.INFO
        ));

        return new ReservationCreatedDTO(saved_reserv.getId().toString(), reservation.getReservation_date());
    }

    public CancelReservationDTO cancelReservation(String token, String reservationId){
        Reservation reservation = reservationRepository.findById(UUID.fromString(reservationId))
                .orElseThrow(() -> new NotFound404Exception("Reservation with specified id not found"));

        if(!reservation.getUser().getMail().equals(userService.getUserByToken(token).getMail())){
            throw new NotFound404Exception("Reservation with specified id not found");
        }

        reservation.setStatus(ReservationStatus.CANCELED);

        Reservation saved_reservation = reservationRepository.save(reservation);

        return new CancelReservationDTO(
                "Your reservation to vehicle with plate '" + saved_reservation.getVehicle().getPlate() + "' has been canceled!"
        );

    }

    public List<GetReservationDTO> getReservationByUserId(){
        return null;
    }


}
