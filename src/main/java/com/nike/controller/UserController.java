package com.nike.controller;

import com.nike.model.record.criarUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @PostMapping("/user")
    public static String criarUsuario(@RequestBody criarUser dados){
        return "";

    }

}
