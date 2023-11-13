package com.nike.security;

import com.nike.dominio.usuario.model.Role;
import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.usuario.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var admin = userRepository.findByEmail("admin");
        if (admin == null) {
            admin = new Usuario();
            admin.setEmail("admin");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
