package com.lianne.utils.openai.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Data
@AllArgsConstructor
public class OpenAIClient {

    private final String token;
    @Getter
    private final String model;
    private final RestTemplate restTemplate;

    public ChatCompletionResponse createChatCompletion(
            ChatCompletionRequest request
    ) {

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-type", "application/json");

        HttpEntity<ChatCompletionRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<ChatCompletionResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                ChatCompletionResponse.class
        );

        return responseEntity.getBody();

    }

}
