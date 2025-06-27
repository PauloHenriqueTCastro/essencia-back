package com.essencia.essencia.controller.DTO.products;

import java.math.BigDecimal;

public record ResponseProductsDTO(
        Long id,
        String name,
        BigDecimal price,
        int quantity,
        String productUrl
) {
}
