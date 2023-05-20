package com.nike.model;

import com.nike.model.record.user.CriarUser;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = {
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
    private String senha;

    public Usuario(CriarUser user) {
        this.nome = user.nome();
        this.sobrenome = user.email();
        this.email = user.email();
        this.senha = user.senha();
    }
}
