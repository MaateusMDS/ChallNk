package com.nike.model.record.carrinho;

import com.nike.model.Produto;
import com.nike.model.Usuario;
import jakarta.validation.constraints.NotNull;


public record saveCarrinho(
        @NotNull
        Usuario usuario,
        @NotNull
        Produto produto
) {
}
