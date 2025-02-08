package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Choice {
    @JsonProperty("message")
    Message message;
}

