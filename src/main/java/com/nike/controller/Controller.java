package com.nike.controller;

import com.nike.model.record.criarUser;
import com.nike.model.record.getChat;
import com.nike.gpt.Main;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @PostMapping("/chat")
    public static String chat(@RequestBody getChat request) {

        List<JSONObject> messages = new ArrayList<>();

        JSONObject systemMessage = new JSONObject().put("role", "system").put("content", "Você é um assistente comediante");
        messages.add(systemMessage);

            return Main.chat(request.mensagem(), messages);
    }

    @PostMapping("/user")
    public static String criarUsuario(@RequestBody criarUser dados){
        return "";

    }

}
