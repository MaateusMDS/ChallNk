package com.nike.dominio.usuario.record;

import com.nike.dominio.usuario.model.Role;
import jakarta.validation.constraints.Email;

public record putUser (

        String nome,
        String sobrenome,
        @Email
        String email,
        String senha,
        Role role
) {

}
