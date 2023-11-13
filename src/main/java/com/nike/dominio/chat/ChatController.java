package com.nike.dominio.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

class Message {
    private String content;
    private boolean isUser;

    public String getContent() {
        return content;
    }

    public boolean isUser() {
        return isUser;
    }

    public Message setUser(boolean user) {
        isUser = user;
        return this;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Message(String content, boolean isUser) {
        this.content = content;
        this.isUser = isUser;
    }

    public Message() {
    }

    public Message(String content) {
        this.content = content;
        this.isUser = true;
    }
}

@Controller
@RequestMapping("/chat")

public class ChatController {

    private List<Message> messages = new ArrayList<>();
    @GetMapping
    public String getChatPage(Model model) {
        messages.clear();
        var message = new Message();
        message.setContent("Olá, eu sou o assistente da Nike. Em que posso ajudar?").setUser(false);
        messages.add(message);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/send")
    @ResponseBody
    public List<Message> sendMessage(@RequestParam String content) {
        Message userMessage = new Message();
        userMessage.setContent(content);
        userMessage.setUser(true);
        messages.add(userMessage);

        Message botMessage = new Message();
        botMessage.setContent("No momento estou em desenvolvimento, mas em breve poderei te ajudar!");
        botMessage.setUser(false);
        messages.add(botMessage);

        return messages;
    }
}