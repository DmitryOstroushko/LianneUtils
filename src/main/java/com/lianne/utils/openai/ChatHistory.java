package com.lianne.utils.openai;

import com.lianne.utils.openai.api.Message;
import lombok.Builder;

import java.util.List;

@Builder
public record ChatHistory(
        List<Message> chatMessages
) {
}
