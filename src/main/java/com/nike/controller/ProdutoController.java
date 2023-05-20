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
@RequestMapping
public class ProdutoController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryUser repository;

    @PostMapping("/user")
    @Transactional
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody saveUser dados) {

        this.status.clear();

        try {
            repository.save(new Usuario(dados));
            this.status.put("status", 200);
            Map<String, Object> usuarioMap = new HashMap<>();

            usuarioMap.put("nome", dados.nome());
            usuarioMap.put("sobrenome", dados.sobrenome());
            usuarioMap.put("email", dados.email());

            this.status.put("message",usuarioMap);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {

        this.status.clear();

        try {
            var usuario = repository.findById(id);
            if (usuario.isPresent()) {
                Map<String, Object> usuarioMap = new HashMap<>();
                Usuario user = usuario.get();

                usuarioMap.put("id", user.getId());
                usuarioMap.put("nome", user.getNome());
                usuarioMap.put("sobrenome", user.getSobrenome());
                usuarioMap.put("email", user.getEmail());

                return ResponseEntity.ok(usuarioMap);
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

    @GetMapping("/user")
    public ResponseEntity<Object> getAll(){

        this.status.clear();

        try {
            return ResponseEntity.ok(repository.findAllBy().toArray());
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @DeleteMapping("/user/{id}")
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

    @PutMapping("/user/{id}")
    @Transactional
    public ResponseEntity<Object> putUser(@PathVariable Long id, @RequestBody putUser dados){

        this.status.clear();

        try {
            var user = repository.findById(id);
            if(user.isPresent()) {
                Usuario usuario = repository.getReferenceById(id);

                usuario.putUser(dados);

                this.status.put("status", 200);

                Map<String, Object> usuarioMap = new HashMap<>();

                usuarioMap.put("nome", dados.nome());
                usuarioMap.put("sobrenome", dados.sobrenome());
                usuarioMap.put("email", dados.email());

                this.status.put("message",usuarioMap);
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
