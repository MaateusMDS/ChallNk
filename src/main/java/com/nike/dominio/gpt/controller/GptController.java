package com.nike.dominio.gpt.controller;

import com.nike.dominio.gpt.test.Main;
import com.nike.dominio.gpt.record.getChat;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GptController {

    @PostMapping("api/chat")
    public static String chat(@RequestBody getChat request) {

        List<JSONObject> messages = new ArrayList<>();

        JSONObject systemMessage = new JSONObject().put("role", "system").put("content", "Você é um assistente da nike e foi feito somente para indicar produtos de acordo com caracteristicas/gosto indicados. Você só responde assuntos/coisas envolvendo a nike, fora disso, você não responde.");
        messages.add(systemMessage);

        return Main.chat(request.mensagem(), messages);
    }
}
