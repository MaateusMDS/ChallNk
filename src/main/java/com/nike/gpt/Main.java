package com.nike.gpt;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String API_KEY = System.getenv("API_GPT");

    public static void main(String[] args) {
        List<JSONObject> messages = new ArrayList<>();

        JSONObject systemMessage = new JSONObject().put("role", "system").put("content", "Você é um assistente da nike e foi feito somente para indicar produtos de acordo com caracteristicas/gosto indicados. Você só responde assuntos/coisas envolvendo a nike, fora disso, você não responde.");
        messages.add(systemMessage);

        String user_input = "";

        while (!user_input.equals("Fim")) {
            user_input = JOptionPane.showInputDialog("Insira a pergunta");
            String chatGptReply = chat(user_input, messages);
            System.out.println("Resposta do ChatGPT: " + chatGptReply);
        }
    }

    public static String chat(String user_input, List<JSONObject> messages) {
        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", user_input);
        messages.add(userMessage);

        JSONArray messagesArray = new JSONArray(messages);

        JSONObject requestBody = new JSONObject()
                .put("model", "gpt-3.5-turbo")
                .put("messages", messagesArray)
                .put("max_tokens", 50);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.openai.com/v1/chat/completions");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        try {
            StringEntity requestEntity = new StringEntity(requestBody.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

            JSONObject jsonResponse = new JSONObject(responseString);
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            JSONObject choice = choicesArray.getJSONObject(0);
            JSONObject message = choice.getJSONObject("message");
            String chatGptReply = message.getString("content");

            JSONObject assistantMessage = new JSONObject()
                    .put("role", "assistant")
                    .put("content", chatGptReply);
            messages.add(assistantMessage);
            System.out.println(messages);

            return chatGptReply;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}