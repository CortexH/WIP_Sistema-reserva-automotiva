package com.example.SistemaReservaAutomotiva.dto.output;

import com.example.SistemaReservaAutomotiva.domain.product.ProductCategory;

public record ReturnProductDTO(
        String name,
        String details,
        ProductCategory category,
        String manufacturer,
        Integer quantity,
        Float price,
        String description,
        Integer assurance
) {
}
