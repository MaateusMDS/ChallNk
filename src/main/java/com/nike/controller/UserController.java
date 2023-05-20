package com.nike.controller;

import com.nike.model.Usuario;
import com.nike.model.record.user.putUser;
import com.nike.model.record.user.saveUser;
import com.nike.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryUser repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody saveUser dados) {

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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {

        this.status.clear();

        try {
            var usuario = repository.findById(id);
            if (usuario.isPresent()) {
                status.put("status", 200);
                status.put("message", usuario.stream().toArray());
                return ResponseEntity.ok(status);
            } else {
                this.status.put("status", 404);
                this.status.put("message", "not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.status);
            }
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){

        this.status.clear();

        try {
            return ResponseEntity.ok(repository.findAll().toArray());
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){

        this.status.clear();

        try {
            var user = repository.findById(id);
            if(user.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", user.get().getEmail() + " deleted");
            } else {
                this.status.put("status", 404);
                this.status.put("message", "not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.status);
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
    public ResponseEntity<Object> putUser(@PathVariable Long id, @RequestBody putUser dados){

        this.status.clear();

        try {
            var usuarioId = repository.findById(id);
            if(usuarioId.isPresent()) {
                Usuario usuario = repository.getReferenceById(id);

                usuario.putUser(dados);

                this.status.put("status", 200);
                this.status.put("message",usuarioId.stream().toArray());
            } else {
                this.status.put("status", 404);
                this.status.put("message", "not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.status);
            }
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }
}
