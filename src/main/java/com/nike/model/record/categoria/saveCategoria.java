package com.nike.model.record.categoria;

import jakarta.validation.constraints.NotBlank;

public record saveCategoria(
        @NotBlank
        String nome
) {
}
