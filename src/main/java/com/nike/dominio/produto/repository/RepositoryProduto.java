package com.nike.dominio.produto.repository;

import com.nike.dominio.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduto extends JpaRepository<Produto, Long> {
}
