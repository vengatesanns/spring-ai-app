package com.vengatx.myaiapp.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

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

}
