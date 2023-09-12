package com.nike.dominio.carrinho.record;

import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;
import jakarta.validation.constraints.NotNull;


public record saveCarrinho(
        @NotNull
        Usuario usuario,
        @NotNull
        Produto produto
) {
}
