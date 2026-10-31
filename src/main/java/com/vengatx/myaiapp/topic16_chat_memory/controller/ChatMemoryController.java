package com.vengatx.myaiapp.topic16_chat_memory.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memory")
public class ChatMemoryController {

    private final ChatClient chatClient;

    public ChatMemoryController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("query") String message, @RequestHeader("username") String username) {
        return chatClient.prompt()
                .user(message.trim())
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, username))
                .call()
                .content();
    }
}
