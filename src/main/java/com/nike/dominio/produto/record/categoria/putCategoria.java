package com.nike.dominio.produto.record.categoria;

import jakarta.validation.constraints.NotBlank;

public record putCategoria(
        @NotBlank
        String nome
) {

}
