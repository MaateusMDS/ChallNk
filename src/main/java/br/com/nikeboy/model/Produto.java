package br.com.nikeboy.model;

import jakarta.persistence.*;

import java.util.List;

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
    @Column(name = "NOME_PROD")
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
    private List<Categoria> categoria;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "TB_GENERO_PRODUTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_GENERO",
                            referencedColumnName = "ID_GENERO",
                            foreignKey = @ForeignKey(name = "FK_GENERO")
                    )
            }
    )
    @Column(name = "GENERO")
    @Enumerated(EnumType.STRING)
    private List<Genero> genero;

    @Column(name = "PRECO")
    private float preco;

    public Produto() {
    }

    public Produto(Long id, String nome, List<Categoria> categoria, List<Genero> genero, float preco) {
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

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public List<Genero> getGenero() {
        return genero;
    }

    public void setGenero(List<Genero> genero) {
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

