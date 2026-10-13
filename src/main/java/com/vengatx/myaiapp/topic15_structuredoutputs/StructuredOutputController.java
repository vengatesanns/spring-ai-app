package com.vengatx.myaiapp.topic15_structuredoutputs;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


record SongsInfo(
        String movie,
        String genre,
        String songName
) {}

record Songs(
        String artist,
        List<SongsInfo> songs
) {}


@RestController
@RequestMapping("/structured_op")
public class StructuredOutputController {

    private final ChatClient chatClient;

    public StructuredOutputController(
            @Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/get_songs_info")
    public Songs getSongsInfo(@RequestParam("query") String query) {
        return chatClient.prompt().user(query).call().entity(Songs.class);
    }

}
