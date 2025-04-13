package com.to_do.tarefas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.json.JSONObject;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class ChatGPTService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;
    @Value("${base.url.openai}")
    private String baseUrlOpenAi;

    public ChatGPTService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("baseUrlOpenAi").build();
    }

    public String perguntar(String pergunta) {
        JSONObject requestBody = new JSONObject()
                .put("model", "gpt-3.5-turbo")
                .put("messages", new org.json.JSONArray()
                        .put(new JSONObject().put("role", "user").put("content", pergunta))
                );

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody.toString())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    JSONObject json = new JSONObject(response);
                    return json
                            .getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content");
                })
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(10))) // tenta 3x com 10s de espera
                .block();
    }
}
