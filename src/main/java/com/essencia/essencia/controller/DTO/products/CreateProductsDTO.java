package com.essencia.essencia.controller.DTO.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductsDTO(
        @NotBlank(message = "Campo obrigatório")
        String name,

        @NotNull(message = "Campo obrigatório")
        BigDecimal price,

        @NotNull(message = "Campo obrigatório")
        int quantity,

        String productUrl,

        @NotNull(message = "Campo obrigatório")
        UUID userId
        ) {
}
