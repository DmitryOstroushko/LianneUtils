package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}