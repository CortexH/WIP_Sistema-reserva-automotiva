package com.example.SistemaReservaAutomotiva.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String mail;
    private String cep;
    private String phone;

    private UserType type;
    private LocalDate register_date;

    private UserAccountStatus status;

    @Column(name = "user_roles")
    private UserRoles userRoles;

}
