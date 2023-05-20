package com.nike.model.record.produto;

import com.nike.model.Categoria;
import com.nike.model.Genero;

public record putProduto(
        String nome,
        Categoria categoria,
        Genero genero,
        float preco
) {

}
