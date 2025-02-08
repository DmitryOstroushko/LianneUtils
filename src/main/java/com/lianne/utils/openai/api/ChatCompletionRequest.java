package com.lianne.utils.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents a request to the OpenAI API for chat-based completions.
 * <p>
 * This class is used to send a request to the OpenAI API, specifying the model to be used and a list of messages
 * that form the context for the chat. The model processes these messages and generates a response based on the input.
 * </p>
 */
@Builder
@Data
@AllArgsConstructor
public class ChatCompletionRequest {

        /**
         * The model to be used for generating the chat completion.
         * <p>
         * This specifies which OpenAI model should be used to process the provided messages (e.g., "gpt-3.5-turbo").
         * </p>
         */
        @JsonProperty("model")
        String model;

        /**
         * The list of messages to be sent to the OpenAI model.
         * <p>
         * This list contains the conversation history, where each message is represented as a {@link Message} object.
         * The model uses this conversation context to generate an appropriate response.
         * </p>
         */
        @JsonProperty("messages")
        List<Message> messages;
}
