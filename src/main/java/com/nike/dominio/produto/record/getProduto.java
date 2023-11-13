package com.nike.dominio.produto.record;

import com.nike.dominio.produto.model.Categoria;
import com.nike.dominio.produto.model.Genero;

import java.util.Set;

public record getProduto(
        Long id,
        String nome,
        Set<Categoria> categoria,
        Genero genero,
        float preco,
        String imagem
) {
}
