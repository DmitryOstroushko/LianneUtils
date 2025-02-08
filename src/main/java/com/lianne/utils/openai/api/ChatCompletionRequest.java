package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ChatCompletionRequest {

        @JsonProperty("model")
        String model;

        @JsonProperty("messages")
        List<Message> messages;

}
