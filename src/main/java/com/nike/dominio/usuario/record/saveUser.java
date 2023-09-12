package com.nike.dominio.usuario.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record saveUser(

        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha
) {
}
