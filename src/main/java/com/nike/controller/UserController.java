package com.nike.controller;

import com.nike.model.Usuario;
import com.nike.model.record.user.CriarUser;
import com.nike.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private RepositoryUser repository;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody CriarUser dados) {
        try {
            repository.save(new Usuario(dados));
            return ResponseEntity.ok("O usuário " + dados.email() + " foi inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.ok("Não foi criar o usuário desejado.");
        }

    }
}
