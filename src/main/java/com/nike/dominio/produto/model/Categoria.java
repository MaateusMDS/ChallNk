package com.nike.dominio.produto.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nike.dominio.produto.record.categoria.putCategoria;
import com.nike.dominio.produto.record.categoria.saveCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "NK_TB_CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UN_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new LinkedHashSet<>();

    public Categoria(saveCategoria categoria) {
        this.nome = categoria.nome();
    }

    public void putCategoria(putCategoria categoria){
        if(categoria.nome() != null){
            this.nome = categoria.nome();
        }
    }
}
