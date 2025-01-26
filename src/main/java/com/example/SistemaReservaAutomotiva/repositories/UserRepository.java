package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByPasswordAndMail(String password, String mail);
    Optional<User> findByMail(String mail);
    Boolean existsByPasswordAndMail(String password, String mail);

}
