package com.nike.controller;

import com.nike.model.Carrinho;
import com.nike.model.Produto;
import com.nike.model.Usuario;
import com.nike.model.record.carrinho.putCarrinho;
import com.nike.model.record.carrinho.saveCarrinho;
import com.nike.repository.RepositoryCarrinho;
import com.nike.repository.RepositoryProduto;
import com.nike.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryCarrinho repository;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryProduto repositoryProduto;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveCarrinho(@RequestBody @Valid saveCarrinho dados) {

        this.status.clear();

        try {

            Usuario usuario = repositoryUser.findById(dados.usuario().getId()).orElse(null);
            Produto produto = repositoryProduto.findById(dados.produto().getId()).orElse(null);

            if (produto == null) {
                this.status.put("status", 400);
                this.status.put("message", "Produto não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }

            if (usuario == null) {
                this.status.put("status", 400);
                this.status.put("message", "Usuário não encontrado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            }

            var carrinho = new Carrinho();


            carrinho.setProduto(produto);
            carrinho.setUsuario(usuario);

            var save = repository.save(carrinho);

            this.status.put("status", 200);
            this.status.put("message",save);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
        return ResponseEntity.ok(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategoriaById(@PathVariable Long id) {

        this.status.clear();

        try {
            var carrinho = repository.findById(id);
            if (carrinho.isPresent()) {
                this.status.put("status", 200);
                this.status.put("message", carrinho);
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Carrinho não encontrado.");
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
    public ResponseEntity<Object> deleteCarrinho(@PathVariable @Valid Long id){

        this.status.clear();

        try {
            var carrinho = repository.findById(id);
            if(carrinho.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", "carrinho" + carrinho.get().getUsuario() + " deleted");
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Carrinho não encontrado.");
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
    public ResponseEntity<Object> putCarrinho(@PathVariable Long id, @RequestBody putCarrinho dados){

        this.status.clear();

        try {
            var carrinhoID = repository.findById(id);
            if(carrinhoID.isPresent()) {
                var carrinho = repository.getReferenceById(id);

                carrinho.putCarrinho(dados);

                this.status.put("status", 200);
                this.status.put("message",carrinho);
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Carrinho não encontrado.");
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
