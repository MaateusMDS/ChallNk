package com.nike.model.record.carrinho;

import com.nike.model.Produto;
import com.nike.model.Usuario;
import jakarta.validation.constraints.NotBlank;


public record saveCarrinho(
        @NotBlank
        Usuario usuario,
        @NotBlank
        Produto produto
) {
}
