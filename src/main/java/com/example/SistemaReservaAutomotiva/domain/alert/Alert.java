package com.example.SistemaReservaAutomotiva.domain.alert;

import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String message;

    @ManyToOne
    @JsonBackReference
    private User user;

    AlertType type;
    AlertState state;

    LocalDateTime emissionTime;

}
