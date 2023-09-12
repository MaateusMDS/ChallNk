package com.nike.dominio.usuario.record;

public record getUser(
        Long id,
        String nome,
        String sobrenome,
        String email
) {
}
