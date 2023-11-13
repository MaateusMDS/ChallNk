package com.nike.dominio.produto.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nike.dominio.produto.model.Categoria;
import com.nike.dominio.produto.model.Genero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


public record saveProduto(
        @NotBlank
        String nome,
        @NotNull
        Set<String> categoria,
        @NotNull
        Genero genero,
        @NotNull @Min(1)
        float preco,
        @NotNull
        String imagem
) {
}