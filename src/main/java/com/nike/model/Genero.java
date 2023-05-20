package com.nike.model;

import lombok.*;

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
