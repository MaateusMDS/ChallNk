package com.nike.model.record.produto;

import com.nike.model.Categoria;
import com.nike.model.Genero;

import java.util.Set;

public record putProduto(
        String nome,
        Set<Categoria> categoria,
        Genero genero,
        float preco
) {

}
