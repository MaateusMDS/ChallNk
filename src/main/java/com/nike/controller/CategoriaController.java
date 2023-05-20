package com.nike.controller;

import com.nike.model.Categoria;
import com.nike.model.record.categoria.putCategoria;
import com.nike.model.record.categoria.saveCategoria;
import com.nike.repository.RepositoryCategoria;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryCategoria repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveCategoria(@RequestBody saveCategoria dados) {

        this.status.clear();

        try {
            repository.save(new Categoria(dados));
            this.status.put("status", 200);

            this.status.put("message",dados);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategoriaById(@PathVariable Long id) {

        this.status.clear();

        try {
            var categoriaID = repository.findById(id);
            if (categoriaID.isPresent()) {
                Map<String, Object> usuarioMap = new HashMap<>();
                Categoria categoria = categoriaID.get();

                usuarioMap.put("id", categoria.getId());
                usuarioMap.put("nome", categoria.getNome());

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
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id){

        this.status.clear();

        try {
            var categoria = repository.findById(id);
            if(categoria.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", categoria.get().getNome() + " deleted");
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
    public ResponseEntity<Object> putCategoria(@PathVariable Long id, @RequestBody putCategoria dados){

        this.status.clear();

        try {
            var categoriaId = repository.findById(id);
            if(categoriaId.isPresent()) {
                Categoria categoria = repository.getReferenceById(id);

                categoria.putCategoria(dados);

                this.status.put("status", 200);

                Map<String, Object> usuarioMap = new HashMap<>();

                usuarioMap.put("nome", dados.nome());

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
