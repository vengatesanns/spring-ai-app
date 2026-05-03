package com.vengatx.myaiapp.topic11_prompttemplate;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PromptTemplateController {


    private final ChatClient chatClient;

    @GetMapping("/prompttemplate")
    public String getPromptResponse(@RequestParam("query") String query) {
     return null;
    }


}
