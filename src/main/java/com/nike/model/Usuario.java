package com.nike.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "NK_TB_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "DS_EMAIL")
})

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(
            name = "SQ_USUARIO",
            sequenceName = "SQ_USUARIO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_USUARIO")
    private Long id;
    @Column(name = "NM_USUARIO")
    private String nome;
    @Column(name = "NM_SOBRENOME")
    private String sobrenome;
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA")
    @JsonIgnore
    private String senha;

    public Usuario(saveUser user) {
        this.nome = user.nome();
        this.sobrenome = user.email();
        this.email = user.email();
        this.senha = user.senha();
    }

    public void putUser(putUser user){
        if(user.nome() != null){
            this.nome = user.nome();
        }
        if(user.sobrenome() != null){
            this.sobrenome = user.sobrenome();
        }
        if(user.email() != null){
            this.email = user.email();
        }
        if(user.senha() != null){
            this.senha = user.senha();
        }
    }
}
