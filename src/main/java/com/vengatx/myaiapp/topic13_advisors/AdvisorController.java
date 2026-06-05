package com.vengatx.myaiapp.topic13_advisors;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-client/advisor")
@RequiredArgsConstructor
public class AdvisorController {

    private final ChatClient chatClient;

    @GetMapping("/logger")
    public String getChatClientLoggerAdvisor(@RequestParam("query") String query) {
        return chatClient.prompt().user(query)
                .advisors(new SimpleLoggerAdvisor(),
                        new UserPreferenceAdvisor(),
                        new SafeGuardAdvisor(List.of("fool")))
                .call().content();
    }


}
