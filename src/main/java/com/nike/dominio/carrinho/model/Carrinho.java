package com.nike.dominio.carrinho.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.carrinho.record.putCarrinho;
import com.nike.dominio.carrinho.record.saveCarrinho;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NK_TB_CARRINHO")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "NK_TB_PRODUTO_CARRINHO",
            joinColumns = {@JoinColumn(name = "ID_CARRINHO", referencedColumnName = "ID_CARRINHO", foreignKey = @ForeignKey(name = "FK_CARRINHO"))},
            inverseJoinColumns = {@JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO"))}
    )
    private List<Produto> produtos;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_CARRINHO_USUARIO"))
    @JsonBackReference
    private Usuario usuario;

    public Carrinho(saveCarrinho carrinho) {
        this.usuario = carrinho.usuario();
        this.produtos = carrinho.produtos();
    }

    public void putCarrinho(putCarrinho carrinho){
        if(carrinho.produtos() != null){
            this.produtos = carrinho.produtos();
        }
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public void addProduto(Produto produto) {
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        this.produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public void clearProdutos() {
        this.produtos.clear();
    }

    public Carrinho(Usuario usuario) {
        this.usuario = usuario;
        this.produtos = new ArrayList<>();
    }

}