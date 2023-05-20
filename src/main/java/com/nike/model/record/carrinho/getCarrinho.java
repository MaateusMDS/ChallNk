package com.nike.model.record.carrinho;

import com.nike.model.Produto;
import com.nike.model.Usuario;

public record getCarrinho(
        Long id,
        Produto produto,
        Usuario usuario
) {
}
