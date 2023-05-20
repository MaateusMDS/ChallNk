package com.nike.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString

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
    @Getter @Setter
    private Long id;
    @Column(name = "NM_PRODUTO")
    @Getter @Setter
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
    @Getter @Setter
    private Genero genero;

    @Column(name = "NR_PRECO")
    @Getter @Setter
    private float preco;

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
}
