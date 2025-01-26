package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.product.Product;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySeller(User user);

}
