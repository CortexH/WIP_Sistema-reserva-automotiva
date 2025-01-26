package com.example.SistemaReservaAutomotiva.domain.purchase;

import com.example.SistemaReservaAutomotiva.domain.product.Product;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchases")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime purchase_date;


}
