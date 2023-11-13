package com.nike.dominio.produto.controller;

import com.nike.dominio.produto.model.Categoria;
import com.nike.dominio.produto.model.Produto;
import com.nike.dominio.categoria.repository.RepositoryCategoria;
import com.nike.dominio.produto.record.putProduto;
import com.nike.dominio.produto.record.saveProduto;
import com.nike.dominio.produto.repository.RepositoryProduto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    Map<String, Object> status = new HashMap<>();

    @Autowired
    private RepositoryProduto repository;

    @Autowired
    private RepositoryCategoria repositoryCategoria;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> saveProduto(@RequestBody @Valid saveProduto dados) {
        this.status.clear();

        try {
            Set<Categoria> categorias = new LinkedHashSet<>();
            for (String categoriaNome : dados.categoria()) {
                Optional<Categoria> categoriaOptional = Optional.ofNullable(repositoryCategoria.findByNome(categoriaNome));
                if (categoriaOptional.isPresent()) {
                    categorias.add(categoriaOptional.get());
                } else {
                    Categoria newCategoria = new Categoria();
                    newCategoria.setNome(categoriaNome);
                    Categoria savedCategoria = repositoryCategoria.save(newCategoria);
                    categorias.add(savedCategoria);
                }
            }

            Produto produto = new Produto();
            produto.setNome(dados.nome());
            produto.setCategorias(categorias);
            produto.setGenero(dados.genero());
            produto.setPreco(dados.preco());
            produto.setImagem(dados.imagem());

            Produto savedProduto = repository.save(produto);

            this.status.put("status", 200);
            this.status.put("message", savedProduto);

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
                this.status.put("status", 400);
                this.status.put("message", "Produto não encontrado.");
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
    public ResponseEntity<Map<String, Object>> getAllProdutos() {

        this.status.clear();

        try {
            List<Produto> produtos = repository.findAll();
            if (produtos.isEmpty()) {
                this.status.put("status", 400);
                this.status.put("message", "Não há produtos cadastrados.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.status);
            } else {
                this.status.put("status", 200);
                this.status.put("message", produtos.stream().toArray());
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
    public ResponseEntity<Object> deleteProduto(@PathVariable @Valid Long id) {

        this.status.clear();

        try {
            var produto = repository.findById(id);
            if (produto.isPresent()) {
                repository.deleteById(id);

                this.status.put("status", 200);
                this.status.put("message", produto.get().getNome() + " deleted");
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Produto não encontrado.");
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
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody @Valid putProduto dados) {

        this.status.clear();

        try {
            var produtoId = repository.findById(id);
            if (produtoId.isPresent()) {
                Produto produto = repository.getReferenceById(id);

                produto.putProduto(dados);

                this.status.put("status", 200);
                this.status.put("message", produto);
            } else {
                this.status.put("status", 400);
                this.status.put("message", "Produto não encontrado.");
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
