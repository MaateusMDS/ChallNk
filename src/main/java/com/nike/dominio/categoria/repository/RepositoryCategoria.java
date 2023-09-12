package com.nike.dominio.categoria.repository;

import com.nike.dominio.produto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategoria extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
