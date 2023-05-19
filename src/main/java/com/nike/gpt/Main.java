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

        JSONObject systemMessage = new JSONObject()
                .put("role", "system")
                .put("content", "Você é um assistente comediante");
        messages.add(systemMessage);

        String user_input = JOptionPane.showInputDialog("Insira a pergunta");
        String chatGptReply = Chat(user_input, messages);
        System.out.println("Resposta do ChatGPT: " + chatGptReply);
    }

    public static String Chat(String user_input, List<JSONObject> messages) {
        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", user_input);
        messages.add(userMessage);

        JSONArray messagesArray = new JSONArray(messages);

        JSONObject requestBody = new JSONObject()
                .put("model", "gpt-3.5-turbo")
                .put("messages", messagesArray);

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
