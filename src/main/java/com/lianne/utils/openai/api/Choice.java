package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a choice in the OpenAI API response.
 * <p>
 * This class holds a single choice which contains a message. It is used in the context of OpenAI API responses,
 * where the model's response is divided into multiple choices, and each choice may contain a message.
 * </p>
 */
@Data
@AllArgsConstructor
public class Choice {

    /**
     * The message associated with the choice.
     * <p>
     * This field contains the message returned as part of the choice, which could include the content of the model's response.
     * </p>
     */
    @JsonProperty("message")
    Message message;
}

