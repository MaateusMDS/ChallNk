package com.nike.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CARRINHO")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(
            name = "SQ_PRODUTO",
            sequenceName = "SQ_PRODUTO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_CARRINHO")

    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_CARRINHO")
    )
    private Produto produto;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_USUARIO_CARRINHO")
    )
    private Usuario usuario;

    public Carrinho() {
    }
    public Carrinho(Long id, Produto produto) {
        this.id = id;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Carrinho setId(Long id) {
        this.id = id;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public Carrinho setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }
}
