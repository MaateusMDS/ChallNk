package com.nike.dominio.carrinho.controller;

import com.nike.dominio.carrinho.model.Carrinho;
import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.usuario.model.Usuario;
import com.nike.dominio.carrinho.record.putCarrinho;
import com.nike.dominio.carrinho.record.saveCarrinho;
import com.nike.dominio.carrinho.repository.RepositoryCarrinho;
import com.nike.dominio.produto.repository.RepositoryProduto;
import com.nike.dominio.usuario.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/carrinho")
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
            var usuario = repositoryUser.findById(dados.usuario().getId());
            if(usuario.isPresent()) {
                Carrinho carrinho = repository.findByUsuarioId(usuario.get().getId());
                if (carrinho == null) {
                    carrinho = new Carrinho(usuario.get());
                }
                for (var produto : dados.produtos()) {
                    var produtoID = repositoryProduto.findById(produto.getId());
                    if(produtoID.isPresent()) {
                        carrinho.addProduto(produtoID.get());
                    } else {
                        this.status.put("status", 400);
                        this.status.put("message", "Produto não encontrado.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
                    }
                }

                repository.save(carrinho);

                this.status.put("status", 200);
                this.status.put("message", carrinho.getProdutos());
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

    @DeleteMapping("/{id}/produto/{idProduto}")
    @Transactional
    public ResponseEntity<Object> deleteProdutoCarrinho(@PathVariable Long id, @PathVariable Long idProduto){

        this.status.clear();

        try {
            var carrinho = repository.findById(id);
            if(carrinho.isPresent()) {
                var produto = repositoryProduto.findById(idProduto);
                if(produto.isPresent()) {
                    carrinho.get().removeProduto(produto.get());
                    repository.save(carrinho.get());

                    this.status.put("status", 200);
                    this.status.put("message", "Produto removido do carrinho.");
                } else {
                    this.status.put("status", 400);
                    this.status.put("message", "Produto não encontrado.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
                }
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
