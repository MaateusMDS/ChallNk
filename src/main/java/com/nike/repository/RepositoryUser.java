package com.nike.repository;

import com.nike.model.Usuario;
import com.nike.model.record.user.getUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryUser extends JpaRepository<Usuario, Long> {
}
