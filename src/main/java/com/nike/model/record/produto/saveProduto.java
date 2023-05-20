package com.nike.model.record.produto;

import com.nike.model.Categoria;
import com.nike.model.Genero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


public record saveProduto(

        @NotBlank
        String nome,
        @NotNull
        Set<Categoria> categoria,
        @NotNull
        Genero genero,
        @NotNull @Min(1)
        float preco
) {
}
