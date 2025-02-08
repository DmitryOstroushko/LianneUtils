package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatCompletionResponse {
        @JsonProperty("choices")
        List<Choice> choices;
}
