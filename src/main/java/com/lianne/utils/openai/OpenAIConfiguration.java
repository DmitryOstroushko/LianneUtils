package com.lianne.utils.openai;

import com.lianne.utils.openai.api.OpenAIClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

    @Bean
    public OpenAIClient openAIClient(
            @Value("${openai.token}") String openAIToken,
            @Value("${openai.model}") String openAIModel,
            @NotNull RestTemplateBuilder restTemplateBuilder
    ) {
        return new OpenAIClient(openAIToken, openAIModel, restTemplateBuilder.build());
    }
}
