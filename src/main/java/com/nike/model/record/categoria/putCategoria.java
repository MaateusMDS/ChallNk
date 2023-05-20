package com.nike.model.record.categoria;

import jakarta.validation.constraints.NotBlank;

public record putCategoria(
        @NotBlank
        String nome
) {

}
