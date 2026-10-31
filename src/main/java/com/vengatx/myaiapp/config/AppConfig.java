package com.vengatx.myaiapp.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class AppConfig {

/*  DEFAULT MESSAGE
    @Bean("openAIChatClient")
    public ChatClient getOpenAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).defaultSystem("""
                    You are an AI Travel and Hotel Booking Assistant for a travel company.
                
                                Your tasks include:
                                - Assisting users in searching for hotels.
                                - Collecting check-in and check-out dates.
                                - Understanding budget constraints.
                                - Recommending hotels based on user preferences.
                                - Explaining hotel amenities and policies.
                                - Suggesting nearby restaurants and tourist attractions.
                                - Generating travel itineraries.
                                - Providing clear and professional responses.
                
                                If required information is missing, ask clarifying questions before making recommendations.
                """)
                .defaultUser("Hello, list all hotels")
                .build();
    }
*/

    @Primary
    @Bean("openAIChatClient")
    public ChatClient getOpenAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .build();
    }


    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(1000).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }


    @Bean("ollamaChatClient")
    public ChatClient getOllamaChatClient(OllamaChatModel ollamaChatModel, ChatMemory chatMemory) {
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        SimpleLoggerAdvisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        return ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(List.of(messageChatMemoryAdvisor, simpleLoggerAdvisor))
                .build();
    }

}
