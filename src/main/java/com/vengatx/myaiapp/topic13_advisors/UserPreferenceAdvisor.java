package com.vengatx.myaiapp.topic13_advisors;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class UserPreferenceAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        String context = """
                User Details:
                    Preferred Room: Deluxe
                    Loyalty Tier: Gold
                    Preferred City: Bangalore
                """;

        ChatClientRequest updatedReq = chatClientRequest.mutate()
                .prompt(chatClientRequest.prompt().augmentSystemMessage(context)).build();

        return callAdvisorChain.nextCall(updatedReq);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
