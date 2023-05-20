package com.nike.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(
            name = "SQ_CATEGORIA",
            sequenceName = "SQ_CATEGORIA",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(name = "NM_CATEGORIA")
    private String nome;

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Categoria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
