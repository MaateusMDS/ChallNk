package com.nike.dominio.produto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nike.dominio.carrinho.model.Carrinho;
import com.nike.dominio.produto.record.putProduto;
import com.nike.dominio.produto.record.saveProduto;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "NK_TB_PRODUTO")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NM_PRODUTO")
    private String nome;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "NK_TB_CATEGORIA_PRODUTO",
            joinColumns = {@JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO"))},
            inverseJoinColumns = {@JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA", foreignKey = @ForeignKey(name = "FK_CATEGORIA"))}
    )
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "DS_GENERO")
    private Genero genero;

    @Column(name = "NR_PRECO")
    private float preco;

    @Column(name = "DS_IMAGEM", length = 1000)
    private String imagem;

    @JsonBackReference
    @ManyToMany(mappedBy = "produtos")
    private List<Carrinho> carrinhos = new ArrayList<>();

    public Produto addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
        return this;
    }

    public Produto removeCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
        return this;
    }

    public Set<Categoria> getCategorias() {
        return Collections.unmodifiableSet(categorias);
    }

    public Produto(saveProduto produto){
        this.nome = produto.nome();
        this.genero = produto.genero();
        this.preco = produto.preco();
        this.imagem = produto.imagem();

        this.categorias = new HashSet<>();
        for (String categoriaNome : produto.categoria()) {
            Categoria categoria = new Categoria();
            categoria.setNome(categoriaNome);
            this.categorias.add(categoria);
        }
    }

    public void putProduto(putProduto produto) {
        if (produto.nome() != null) {
            this.nome = produto.nome();
        }
        if (produto.preco() > 0) {
            this.preco = produto.preco();
        }
        if (produto.categoria() != null) {
            this.categorias = produto.categoria();
        }
        if (produto.genero() != null) {
            this.genero = produto.genero();
        }
        if (produto.imagem() != null) {
            this.imagem = produto.imagem();
        }
    }
}