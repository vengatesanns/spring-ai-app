package com.vengatx.myaiapp.modelproviders.openai;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAICustomController {

    @GetMapping("/openai/custom-chat")
    public String getOpenAICustomChatMessage(@RequestParam("query") String query) {
        String apiKey = System.getenv("OPEN_AI_KEY");
        OpenAiApi openAiApi = OpenAiApi.builder().apiKey(apiKey).build();
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .model("gpt-4.1-2025-04-14")
                .maxTokens(250)
                .build();
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder().openAiApi(openAiApi).defaultOptions(chatOptions).build();
        return openAiChatModel.call(query);
    }

}
