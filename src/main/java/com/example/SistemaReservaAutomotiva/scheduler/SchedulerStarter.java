package com.example.SistemaReservaAutomotiva.scheduler;

import com.example.SistemaReservaAutomotiva.domain.reservation.Reservation;
import com.example.SistemaReservaAutomotiva.domain.reservation.ReservationStatus;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.domain.user.UserAccountStatus;
import com.example.SistemaReservaAutomotiva.domain.user.UserRoles;
import com.example.SistemaReservaAutomotiva.domain.user.UserType;
import com.example.SistemaReservaAutomotiva.domain.vehicle.Disponibility;
import com.example.SistemaReservaAutomotiva.domain.vehicle.Vehicle;
import com.example.SistemaReservaAutomotiva.domain.vehicle.VehicleBrand;
import com.example.SistemaReservaAutomotiva.repositories.ReservationRepository;
import com.example.SistemaReservaAutomotiva.repositories.UserRepository;
import com.example.SistemaReservaAutomotiva.repositories.VehicleRepository;
import com.example.SistemaReservaAutomotiva.scheduler.Schedules.MessageSystem;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Slf4j
@Component
public class SchedulerStarter {

    @Autowired
    private MessageSystem messageSystem;

    @Scheduled(cron = "0 0 * * * *")
    public void StartMessageSystem(){
        messageSystem.beginMessageProccess();
    }

}
