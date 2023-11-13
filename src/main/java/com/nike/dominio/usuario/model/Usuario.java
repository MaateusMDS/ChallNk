package com.nike.dominio.usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nike.dominio.carrinho.model.Carrinho;
import com.nike.dominio.usuario.record.putUser;
import com.nike.dominio.usuario.record.saveUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Carrinho carrinho;

    public Usuario(saveUser user) {
        this.nome = user.nome();
        this.sobrenome = user.sobrenome();
        this.email = user.email();
        this.senha = user.senha();
        this.role = Role.USER;
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
        if(user.role() != null){
            this.role = user.role();
        }
    }
}
