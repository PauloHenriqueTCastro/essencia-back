package com.essencia.essencia.controller.DTO.products;

import java.math.BigDecimal;

public record PatchProductsDTO(
        String name,
        BigDecimal price,
        Integer quantity,
        String productUrl
) {
}
