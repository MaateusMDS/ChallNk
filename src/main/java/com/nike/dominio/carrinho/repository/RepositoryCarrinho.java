package com.nike.dominio.carrinho.repository;

import com.nike.dominio.carrinho.model.Carrinho;
import com.nike.dominio.carrinho.record.getCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryCarrinho extends JpaRepository<Carrinho, Long> {
    Carrinho findByUsuarioId(Long id);
}
