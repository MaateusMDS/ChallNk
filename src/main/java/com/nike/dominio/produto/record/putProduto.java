package com.nike.dominio.produto.record;

import com.nike.dominio.produto.model.Categoria;
import com.nike.dominio.produto.model.Genero;
import jakarta.validation.constraints.Min;

import java.util.Set;

public record putProduto(
        String nome,
        Set<Categoria> categoria,
        Genero genero,
        @Min(1)
        float preco,
        String imagem
) {

}
