package com.lianne.utils.openai;

import com.lianne.utils.openai.api.ChatCompletionRequest;
import com.lianne.utils.openai.api.Message;
import com.lianne.utils.openai.api.OpenAIClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatGPTService {

    private final OpenAIClient openAIClient;
    private final ChatGPTHistoryService chatGPTHistoryService;

    /**
     * Methods processes request - response messages/dialog for the user
     * @param userId is a user id
     * @param userTextInput is a user request text
     * @return current response from ChatGPT
     */
    public String getResponseChatForUser(
            Long userId,
            String userTextInput
    ) {

        chatGPTHistoryService.createHistoryIfNotExist(userId);
        var history = chatGPTHistoryService.addMessageToHistory(
                userId,
                Message.builder()
                        .content(userTextInput)
                        .role("user")
                        .build()
        );

        var request = ChatCompletionRequest.builder()
                .model(openAIClient.getModel())
                .messages(history.chatMessages())
                .build();

        var response = openAIClient.createChatCompletion(request);

        var messageFromGPT = response.getChoices().get(0).getMessage();
        chatGPTHistoryService.addMessageToHistory(userId, messageFromGPT);

        return messageFromGPT.getContent();
    }
}
