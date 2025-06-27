package com.essencia.essencia.controller.DTO.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUsersDTO(
        @NotBlank(message = "Campo obrigatório")
        @Size(max = 100, min = 2, message = "Campo fora do tamanho padrão")
        String name,

        @NotBlank(message = "Campo obrigatório")
        @Email(message = "Formato do campo invalido")
        String email,

        @NotBlank(message = "Campo obrigatório")
        @Size(max = 50, min = 2, message = "Campo fora do tamanho padrão")
        String password
) {
}
