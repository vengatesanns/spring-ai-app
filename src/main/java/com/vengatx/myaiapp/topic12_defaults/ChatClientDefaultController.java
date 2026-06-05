package com.vengatx.myaiapp.topic12_defaults;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-client/defaults")
@RequiredArgsConstructor
public class ChatClientDefaultController {

    private final ChatClient chatClient;

    @GetMapping("/system")
    public String getChatClientDefault(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().content();
    }

    @GetMapping("/user")
    public String getChatClientUserDefault(@RequestParam("query") String query) {
        return chatClient.prompt()
                .system("You are a chef")
                .user(query)
                .call().content();
    }



}
