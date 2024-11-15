package com.weber.aihelperapps.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public class OpenAIChatHelper {

    private static final String API_KEY = "my_key";
    private static final String BASE_URL = "https://api.aimlapi.com/v1";
    private static final String MODEL = "mistralai/Mistral-7B-Instruct-v0.2";
    private static final String SYSTEM_PROMPT = "You are a travel agent. Be descriptive and helpful.";

    /**
     * Метод для отправки запроса к OpenAI и получения ответа
     *
     * @param userPrompt Сообщение пользователя
     * @return Ответ от OpenAI
     */
    public static String getAIResponse(String userPrompt) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Создаем объект запроса
            HttpPost post = new HttpPost(BASE_URL + "/chat/completions");

            // Создаем объект JSON для запроса
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();

            // Добавляем параметры запроса
            requestBody.put("model", MODEL);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 256);

            // Формируем список сообщений
            ArrayNode messages = objectMapper.createArrayNode();
            messages.add(objectMapper.createObjectNode()
                    .put("role", "system")
                    .put("content", SYSTEM_PROMPT));
            messages.add(objectMapper.createObjectNode()
                    .put("role", "user")
                    .put("content", userPrompt));

            requestBody.set("messages", messages);

            // Устанавливаем тело запроса
            StringEntity entity = new StringEntity(requestBody.toString());
            post.setEntity(entity);

            // Устанавливаем заголовки
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + API_KEY);

            // Выполняем запрос
            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity);

                // Извлекаем ответ из JSON и возвращаем его
                return parseResponse(responseString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while processing the request";
        }
    }

    // Метод для извлечения ответа AI из JSON
    private static String parseResponse(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseNode = objectMapper.readTree(response).deepCopy();
        return responseNode.path("choices").get(0).path("message").path("content").asText();
    }

    public static void main(String[] args) {
        String userPrompt = "Tell me about San Francisco";
        String aiResponse = getAIResponse(userPrompt);

        System.out.println("User: " + userPrompt);
        System.out.println("AI: " + aiResponse);
    }
}
