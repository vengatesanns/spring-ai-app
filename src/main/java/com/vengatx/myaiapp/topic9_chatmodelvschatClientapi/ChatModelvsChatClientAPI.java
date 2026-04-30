package com.vengatx.myaiapp.topic9_chatmodelvschatClientapi;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModelvsChatClientAPI {


    private final OpenAiChatModel openAiChatModel;
    private final ChatClient chatClient;

    public ChatModelvsChatClientAPI(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.chatClient = ChatClient.builder(openAiChatModel).build();
    }

    @GetMapping("/chatmodel")
    public String getChatModelResponse(@RequestParam("prompt") String prompt) {
        return openAiChatModel.call(prompt);
    }

    @GetMapping("/chatclient-api")
    public String getChatClientAPIResponse(@RequestParam("prompt") String prompt) {
        return chatClient.prompt(prompt).call().content();
    }

}
