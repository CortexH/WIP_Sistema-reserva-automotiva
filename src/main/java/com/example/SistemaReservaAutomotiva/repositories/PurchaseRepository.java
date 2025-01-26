package com.example.SistemaReservaAutomotiva.repositories;

import com.example.SistemaReservaAutomotiva.domain.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}
