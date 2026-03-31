package com.vengatx.myaiapp.modelproviders.huggingface;


import org.springframework.ai.huggingface.HuggingfaceChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hugging-face")
public class HuggingFaceController {


    @Autowired
    private HuggingfaceChatModel huggingfaceChatModel;


    @GetMapping("/chat")
    public String getChatResponse(@RequestParam("query") String message) {
        return huggingfaceChatModel.call(message);
    }


}
