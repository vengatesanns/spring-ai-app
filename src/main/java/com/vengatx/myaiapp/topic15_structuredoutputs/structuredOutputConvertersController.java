package com.vengatx.myaiapp.topic15_structuredoutputs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/converters")
public class structuredOutputConvertersController {

    private final ChatClient chatClient;

    public structuredOutputConvertersController(
            @Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/bean-converter")
    public Songs getSongsInfoByBeanConverter(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().entity(new BeanOutputConverter<>(Songs.class));
    }


    @GetMapping("/list-converter")
    public List<String> getSongsInfoByListConverter(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().entity(new ListOutputConverter());
    }


    @GetMapping("/map-converter")
    public Map<String, Object> getSongsInfoByMapConverter(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().entity(new MapOutputConverter());
    }

    @GetMapping("/listbean-converter")
    public List<Songs> getSongsInfoByListBeanConverter(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().entity(
                new ParameterizedTypeReference<List<Songs>>() {
                }
        );
    }


}
