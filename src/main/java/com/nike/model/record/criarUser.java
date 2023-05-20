package com.nike.model.record;

import jakarta.persistence.Column;

public record criarUser(

        Long id,
        String nome,
        String sobrenome,
        String email,
        String senha
) {
}
