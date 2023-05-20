package com.nike.model.record.produto;

import com.nike.model.Categoria;
import com.nike.model.Genero;
import com.nike.model.Produto;

public record saveProduto(

        String nome,
        Categoria categoria,
        Genero genero,
        float preco
) {
}
