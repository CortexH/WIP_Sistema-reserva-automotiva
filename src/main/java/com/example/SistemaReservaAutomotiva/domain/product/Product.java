package com.example.SistemaReservaAutomotiva.domain.product;

import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String details;

    private ProductCategory category;
    
    private String manufacturer;
    private Integer quantity;

    private Float sell_price;

    private String description;

    @Column(nullable = true)
    private Integer assurance_months;

    @ManyToOne
    @JsonBackReference
    private User seller;

}
