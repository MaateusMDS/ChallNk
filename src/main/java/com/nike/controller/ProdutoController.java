package com.nike.controller;

import com.nike.model.Produto;
import com.nike.model.record.produto.putProduto;
import com.nike.model.record.produto.saveProduto;
import com.nike.repository.RepositoryProduto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryProduto repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveProduto(@RequestBody saveProduto dados) {

        this.status.clear();

        try {
            var produto = repository.save(new Produto(dados));
            this.status.put("status", 200);
            this.status.put("message",produto);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProdutoById(@PathVariable Long id) {

        this.status.clear();

        try {
            var produtoId = repository.findById(id);
            if (produtoId.isPresent()) {
                status.put("status", 200);
                status.put("message", produtoId.stream().toArray());
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
        return ResponseEntity.ok(status);
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){

        this.status.clear();

        try {
            this.status.put("status", 200);
            this.status.put("message", repository.findAll().toArray());
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id){

        this.status.clear();

        try {
            var produto = repository.findById(id);
            if(produto.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", produto.get().getNome() + " deleted");
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
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody putProduto dados){

        this.status.clear();

        try {
            var produtoId = repository.findById(id);
            if(produtoId.isPresent()) {
                Produto produto = repository.getReferenceById(id);

                produto.putProduto(dados);

                this.status.put("status", 200);
                this.status.put("message",produto);
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
