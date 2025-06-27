package com.essencia.essencia.controller.DTO.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "campo obrigatório")
        @Email(message = "Formato do campo invalido")
        String email,
        @NotBlank(message = "campo obrigatório")
        String password) {
}
