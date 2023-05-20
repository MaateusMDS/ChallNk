package com.nike.model;

import com.nike.model.record.categoria.putCategoria;
import com.nike.model.record.categoria.saveCategoria;
import com.nike.model.record.user.putUser;
import com.nike.model.record.user.saveUser;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

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

    public Categoria(saveCategoria categoria) {
        this.nome = categoria.nome();
    }

    public void putUser(putCategoria categoria){
        if(categoria.nome() != null){
            this.nome = categoria.nome();
        }
    }
}
