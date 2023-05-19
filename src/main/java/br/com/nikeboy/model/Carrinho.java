package br.com.nikeboy.model;

import jakarta.persistence.*;

@Entity
@Table
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(
            name = "SQ_PRODUTO",
            sequenceName = "SQ_PRODUTO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_PRODUTO")

    private Long id;
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
