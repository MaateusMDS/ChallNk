package com.nike.dominio.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public enum Genero {
    FEMININO(0, "Feminino", 'F'), MASCULINO(1, "Masculino", 'M'), UNISEX(2, "Unisex", 'U');

    private int id;
    private String nome;
    private Character sigla;

}
