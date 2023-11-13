package com.nike.dominio.carrinho.record;

import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;

import java.util.List;

public record putCarrinho(
        Usuario usuario,
        List<Produto> produtos
) {

}
