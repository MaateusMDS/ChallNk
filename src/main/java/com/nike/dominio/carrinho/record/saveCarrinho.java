package com.nike.dominio.carrinho.record;

import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record saveCarrinho(
        @NotNull
        Usuario usuario,
        @NotNull
        List<Produto> produtos
) {
}
