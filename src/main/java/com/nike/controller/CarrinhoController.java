package com.nike.controller;

import com.nike.model.Carrinho;
import com.nike.model.record.carrinho.putCarrinho;
import com.nike.model.record.carrinho.saveCarrinho;
import com.nike.repository.RepositoryCarrinho;
import jakarta.transaction.Transactional;
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

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveCarrinho(@RequestBody saveCarrinho dados) {

        this.status.clear();

        try {

            var carrinho = new Carrinho(dados);

            repository.save(carrinho);

            this.status.put("status", 200);
            Map<String, Object> carrinhoMap = new HashMap<>();

            carrinhoMap.put("usuario", dados.usuario());
            carrinhoMap.put("produto", dados.produto());

            this.status.put("message",carrinhoMap);

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCarrinhoById(@PathVariable Long id) {

        this.status.clear();

        try {
            var carrinhoId= repository.findById(id);
            if (carrinhoId.isPresent()) {
                Map<String, Object> carrinhoMap = new HashMap<>();
                Carrinho carrinho = carrinhoId.get();

                carrinhoMap.put("id", carrinho.getId());
                carrinhoMap.put("usuario", carrinho.getUsuario());
                carrinhoMap.put("produto", carrinho.getProduto());

                return ResponseEntity.ok(carrinhoMap);
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
            return ResponseEntity.ok(repository.findAllBy().toArray());
        } catch (Exception e) {
            this.status.put("status", 500);
            this.status.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteCarrinho(@PathVariable Long id){

        this.status.clear();

        try {
            var carrinho = repository.findById(id);
            if(carrinho.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", "carrinho" + carrinho.get().getUsuario() + " deleted");
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
    public ResponseEntity<Object> putCarrinho(@PathVariable Long id, @RequestBody putCarrinho dados){

        this.status.clear();

        try {
            var carrinhoID = repository.findById(id);
            if(carrinhoID.isPresent()) {
                var carrinho = repository.getReferenceById(id);

                carrinho.putCarrinho(dados);

                this.status.put("status", 200);

                Map<String, Object> carrinhoMap = new HashMap<>();

                carrinhoMap.put("id", carrinho.getId());
                carrinhoMap.put("usuario", carrinho.getUsuario());
                carrinhoMap.put("produto", carrinho.getProduto());

                this.status.put("message",carrinhoMap);
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
