package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a message in the OpenAI API request or response.
 * <p>
 * This class is used to encapsulate a message that includes a role and content. The role indicates
 * the sender (e.g., user or assistant) and the content contains the text of the message.
 * </p>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /**
     * The role of the sender of the message.
     * <p>
     * This field can be "user", "assistant", or other values based on the OpenAI API's specific use case.
     * </p>
     */
    @JsonProperty("role")
    private String role;

    /**
     * The content of the message.
     * <p>
     * This field holds the text or information that is part of the message, typically a prompt or response.
     * </p>
     */
    @JsonProperty("content")
    private String content;
}