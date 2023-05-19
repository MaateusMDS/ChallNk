package com.nike.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @PostMapping("/chat")
    public static String chat(){
        return "Hello World!";
    }

}
