package com.nike.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "TB_PRODUTO"
)
public class Produto {
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
    @Column(name = "NM_PRODUTO")
    private String nome;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "TB_CATEGORIA_PRODUTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_CATEGORIA",
                            referencedColumnName = "ID_CATEGORIA",
                            foreignKey = @ForeignKey(name = "FK_CATEGORIA")
                    )
            }
    )
    private Set<Categoria> categoria = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "DS_GENERO")
    private Genero genero;

    @Column(name = "NR_PRECO")
    private float preco;

    public Produto() {
    }

    public Produto(Long id, String nome, Set<Categoria> categoria, Genero genero, float preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.genero = genero;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Produto addCategoria(Categoria cat){
        this.categoria.add(cat);
        return this;
    }

    public Produto removeCategoria(Produto cat){
        this.categoria.remove(cat);
        return this;
    }

    public Set<Categoria> getCategorias(){
        return Collections.unmodifiableSet(categoria);
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public float getPreco() {
        return preco;
    }

    public Produto setPreco(float preco) {
        this.preco = preco;
        return this;

    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", genero=" + genero +
                ", preco=" + preco +
                '}';
    }
}

