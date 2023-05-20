package com.nike.model;

import com.nike.model.record.carrinho.putCarrinho;
import com.nike.model.record.carrinho.saveCarrinho;
import com.nike.model.record.categoria.putCategoria;
import com.nike.model.record.categoria.saveCategoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CARRINHO")

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_CARRINHO")
    )
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_USUARIO_CARRINHO")
    )
    private Usuario usuario;

    public Carrinho(saveCarrinho carrinho) {
        this.usuario = carrinho.usuario();
        this.produto = carrinho.produto();
    }

    public void putCarrinho(putCarrinho carrinho){
        if(carrinho.produto() != null){
            this.produto = carrinho.produto();
        }
        if(carrinho.usuario() != null){
            this.usuario = carrinho.usuario();
        }
    }

}
