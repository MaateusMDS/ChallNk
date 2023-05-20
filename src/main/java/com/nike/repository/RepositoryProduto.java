package com.nike.repository;

import com.nike.model.Produto;
import com.nike.model.Usuario;
import com.nike.model.record.user.getUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryProduto extends JpaRepository<Produto, Long> {
}
