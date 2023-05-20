package com.nike.repository;

import com.nike.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<Usuario, Long> {
}
