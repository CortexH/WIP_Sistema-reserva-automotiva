package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.alert.Alert;
import com.example.SistemaReservaAutomotiva.domain.alert.AlertState;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, UUID> {

    List<Alert> findAllByUser(User user);


}
