package com.nike.dominio.carrinho.record;

import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;

public record getCarrinho(
        Long id,
        Produto produto,
        Usuario usuario
) {
}
