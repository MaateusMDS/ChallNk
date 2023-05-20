package com.nike.model.record.user;

import jakarta.validation.constraints.Email;

public record putUser (

        String nome,
        String sobrenome,
        @Email
        String email,
        String senha
) {

}
