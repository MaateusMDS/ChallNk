package com.nike.dominio.usuario.controller;

import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.usuario.record.putUser;
import com.nike.dominio.usuario.record.saveUser;
import com.nike.dominio.usuario.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("api/user")
public class UserController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryUser repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody @Valid saveUser dados) {

        this.status.clear();

        try {
            Usuario usuario = new Usuario(dados);
            usuario.setSenha(passwordEncoder.encode(dados.senha()));
            repository.save(usuario);
            this.status.put("status", 200);
            this.status.put("message", usuario);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {

        this.status.clear();

        try {
            var usuario = repository.findById(id);
            if (usuario.isPresent()) {
                status.put("status", 200);
                status.put("message", usuario.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllUsers() {

        this.status.clear();

        try {
            List<Usuario> usuarios = repository.findAll();
            if (usuarios.isEmpty()) {
                this.status.put("status", 400);
                this.status.put("message", "Não há usuários cadastrados.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            } else {
                this.status.put("status", 200);
                this.status.put("message", usuarios.stream().toArray());
            }
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteUser(@PathVariable @Valid Long id) {

        this.status.clear();

        try {
            var user = repository.findById(id);
            if (user.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", user.get().getEmail() + " deleted");
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> putUser(@PathVariable Long id, @RequestBody @Valid putUser dados) {

        this.status.clear();

        try {
            var usuarioId = repository.findById(id);
            if (usuarioId.isPresent()) {
                Usuario usuario = repository.getReferenceById(id);

                usuario.putUser(dados);

                this.status.put("status", 200);
                this.status.put("message", usuarioId.stream().toArray());
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }
}