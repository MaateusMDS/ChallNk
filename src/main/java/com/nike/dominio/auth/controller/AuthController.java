package com.nike.dominio.auth.controller;

import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.usuario.record.saveUser;
import com.nike.dominio.usuario.repository.RepositoryUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("api/auth")
public class AuthController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryUser repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Param("email") String email, @Param("senha") String senha) {

        this.status.clear();

        try {
            var usuario = repository.findByEmail(email);
            if (usuario != null) {
                if (passwordEncoder.matches(senha, usuario.getSenha())) {
                    this.status.put("status", 200);
                    this.status.put("message", usuario);
                } else {
                    this.status.put("status", 401);
                    this.status.put("message", "Senha incorreta.");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this.status);
                }
            } else {
                this.status.put("status", 404);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.status);
            }
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody @Valid saveUser dados) {

        this.status.clear();

        try {
            Usuario usuario = repository.save(new Usuario(dados));
            this.status.put("status", 200);
            this.status.put("message", usuario);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

}
