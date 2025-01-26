package com.example.SistemaReservaAutomotiva.dto.input;

import com.example.SistemaReservaAutomotiva.domain.product.ProductCategory;

public record NewProductDTO(
        String name,
        ProductCategory category,
        String manufacturer,
        Integer quantity,
        Float price,
        String description,
        Integer assurance_months,
        String details
) {
}
