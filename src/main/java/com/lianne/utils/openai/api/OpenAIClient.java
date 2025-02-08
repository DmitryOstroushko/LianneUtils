package com.lianne.utils.openai.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * Client class for interacting with the OpenAI API.
 * <p>
 * This class provides a method for creating chat completions using the OpenAI API.
 * It handles the communication with the OpenAI endpoint, sending a request and receiving the response.
 * </p>
 */
@Data
@AllArgsConstructor
public class OpenAIClient {

    /**
     * The API token used for authentication with the OpenAI API.
     */
    private final String token;

    /**
     * The model to be used for chat completion requests (e.g., "gpt-3.5-turbo").
     */
    @Getter
    private final String model;

    /**
     * The {@link RestTemplate} used for making HTTP requests to the OpenAI API.
     */
    private final RestTemplate restTemplate;

    /**
     * Creates a chat completion by sending a request to the OpenAI API.
     * <p>
     * This method sends a POST request to the OpenAI chat completion endpoint with the provided request object.
     * It includes the required authentication token and sets the appropriate headers for the request.
     * The method then returns the response from OpenAI as a {@link ChatCompletionResponse} object.
     * </p>
     *
     * @param request The {@link ChatCompletionRequest} object containing the conversation context.
     * @return The {@link ChatCompletionResponse} object containing the model's response.
     */
    public ChatCompletionResponse createChatCompletion(ChatCompletionRequest request) {

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
