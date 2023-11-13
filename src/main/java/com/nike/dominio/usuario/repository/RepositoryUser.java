package com.nike.dominio.usuario.repository;

import com.nike.dominio.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
