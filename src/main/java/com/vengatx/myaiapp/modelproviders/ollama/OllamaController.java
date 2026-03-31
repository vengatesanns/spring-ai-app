package com.vengatx.myaiapp.modelproviders.ollama;


import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama")
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @GetMapping("/chat")
    public String getChatMessage(@RequestParam("query") String query) {
        return ollamaChatModel.call(query);
    }


}
