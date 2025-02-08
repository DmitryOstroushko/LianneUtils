package com.lianne.utils.openai;

import com.lianne.utils.openai.api.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ChatGPTHistoryService {

    private final Map<Long, ChatHistory> chatHistoryMap = new ConcurrentHashMap<>();

    public Optional<ChatHistory> getUserHistory(
            Long userId
    ) {
        return Optional.ofNullable(chatHistoryMap.get(userId));
    }

    public void createHistory(
            Long userId
    ) {
        chatHistoryMap.put(userId, new ChatHistory(new ArrayList<>()));
    }

    public void clearHistory(
            Long userId
    ) {
        chatHistoryMap.remove(userId);
    }

    public ChatHistory addMessageToHistory(
            Long userId,
            Message message
    ) {
        var chatHistory = chatHistoryMap.get(userId);
        if (chatHistory == null)
            throw new IllegalStateException("History for user %s doesn't exist".formatted(userId));

        chatHistory.chatMessages().add(message);

        return chatHistory;
    }

    public void createHistoryIfNotExist(
            Long userId
    ) {
        if (!chatHistoryMap.containsKey(userId)) {
            createHistory(userId);
        }
    }
}
