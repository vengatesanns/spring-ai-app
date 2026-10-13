package com.vengatx.myaiapp.topic14_multimodal;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultimodalityController {

    private final ChatClient chatClient;
    private final OllamaChatModel ollamaChatModel;

    public MultimodalityController(
            @Qualifier("ollamaChatClient") ChatClient chatClient,
            OllamaChatModel ollamaChatModel) {
        this.chatClient = chatClient;
        this.ollamaChatModel = ollamaChatModel;
    }

    @GetMapping("/multimodal")
    public String getMultiModalQuery(@RequestParam String query) {
        var imageResource = new ClassPathResource("/images/multimodal-game.png");
        Media media = new Media(MimeTypeUtils.IMAGE_PNG, imageResource);
        UserMessage userMessage = UserMessage.builder()
                .text(query)
                .media(media)
                .build();
        return chatClient.prompt().messages(userMessage).call().content();
    }


    @GetMapping("/multimodal/chat-model")
    public String getMultiModalChatModelQuery(@RequestParam String query) {
        var imageResource = new ClassPathResource("/images/multimodal-game.png");
        Media media = new Media(MimeTypeUtils.IMAGE_PNG, imageResource);
        UserMessage userMessage = UserMessage.builder()
                .text(query)
                .media(media)
                .build();

        OllamaChatOptions ollamaChatOptions = OllamaChatOptions.builder().model("gemma3:latest").build();
        Prompt prompt = Prompt.builder().messages(userMessage).chatOptions(ollamaChatOptions).build();
        return ollamaChatModel.call(prompt).getResult().getOutput().getText();
    }

}
