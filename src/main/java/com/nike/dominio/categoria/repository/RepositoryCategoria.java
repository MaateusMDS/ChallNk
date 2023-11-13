package com.nike.dominio.categoria.repository;

import com.nike.dominio.produto.model.Categoria;
import com.nike.dominio.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.List;

public interface RepositoryCategoria extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
