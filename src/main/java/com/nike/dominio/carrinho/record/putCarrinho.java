package com.nike.dominio.carrinho.record;

import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;

public record putCarrinho(
        Produto produto,
        Usuario usuario
) {

}
