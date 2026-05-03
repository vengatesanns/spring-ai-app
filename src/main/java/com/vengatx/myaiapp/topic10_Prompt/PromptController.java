package com.vengatx.myaiapp.topic10_Prompt;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final ChatClient chatClient;

    @GetMapping("/prompt1")
    public String getPromptResponse(@RequestParam("query") String query) {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new UserMessage(query));
        messageList.add(new SystemMessage(" You are a salesman in Honda Bike Showroom, Provide Bike details about that brand"));

        Prompt prompt = new Prompt(messageList);

        String response = chatClient.prompt(prompt).call().content();

        messageList.add(new AssistantMessage(response));
        messageList.add(new UserMessage("Can I get price of the bikes?"));

        String response2 = chatClient.prompt(prompt).call().content();


        String response3 = chatClient.prompt()
                .system("only about tech")
                .user("Tell me some jokes?")
                .call().content();

        return response3;
    }

}
