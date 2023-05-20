package com.nike.repository;

import com.nike.model.Carrinho;
import com.nike.model.Usuario;
import com.nike.model.record.carrinho.getCarrinho;
import com.nike.model.record.user.getUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryCarrinho extends JpaRepository<Carrinho, Long> {
    List<getCarrinho> findAllBy();

}
