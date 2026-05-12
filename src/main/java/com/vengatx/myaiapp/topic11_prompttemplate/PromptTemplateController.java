package com.vengatx.myaiapp.topic11_prompttemplate;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PromptTemplateController {

    private final ChatClient chatClient;

    @Value("classpath:/prompttemplates/ecommerce-support-system-msg.st")
    private Resource systemMessageResource;

    @Value("classpath:/prompttemplates/ecommerce-support-user-msg.st")
    private Resource userMessageResource;

    @PostMapping("/prompttemplate")
    public String getPromptResponse(@RequestBody PromptTemplateReq promptTemplateReq) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are {bot_name}, a customer support assistant for an e-commerce platform.
                
                Rules:
                - Be polite and professional
                - Help the customer clearly
                - Keep responses short and friendly
                - Apologize if there is an issue
                
                Customer Details:
                - Name: {name}
                - Order ID: {order_id}
                - Product: {product}
                - Issue: {issue}
                
                Support Email: {support_email}
                
                Respond politely and help the customer.
                """);

        Map<String, Object> inputs = Map.of(
                "bot_name", "Alice",
                "name", promptTemplateReq.getCustomerName(),
                "order_id", promptTemplateReq.getOrderId(),
                "product", promptTemplateReq.getProduct(),
                "issue", promptTemplateReq.getIssue(),
                "support_email", "support@vengatstore.com"
        );

        Prompt prompt = promptTemplate.create(inputs);

        return chatClient.prompt(prompt).call().content();
    }


    @PostMapping("/prompttemplate/message-roles")
    public String getPromptTemplateResponseMsgRoles(@RequestBody PromptTemplateReq promptTemplateReq) {
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate("""
                You are {bot_name}, a customer support assistant for an e-commerce platform.
                
                Rules:
                - Be polite and professional
                - Help the customer clearly
                - Keep responses short and friendly
                - Apologize if there is an issue
                
                Support Email: {support_email}
                
                Respond politely and help the customer.
                
                """);

        PromptTemplate userPromptTemplate = new PromptTemplate("""
                Customer Name: {name}
                Order ID: {order_id}
                Product: {product}
                Issue: {issue}
                
                Generate a response for the customer based on the above details.
                """);

        Map<String, Object> sysInputs = Map.of(
                "bot_name", "Alice",
                "support_email", "support@vengatstore.com"
        );
        Message systemMessage = systemPromptTemplate.createMessage(sysInputs);

        Map<String, Object> userInputs = Map.of(
                "name", promptTemplateReq.getCustomerName(),
                "order_id", promptTemplateReq.getOrderId(),
                "product", promptTemplateReq.getProduct(),
                "issue", promptTemplateReq.getIssue()
        );
        Message userMessage = userPromptTemplate.createMessage(userInputs);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return chatClient.prompt(prompt).call().content();
    }


    @PostMapping("/prompttemplate/custom-renderer")
    public String getPromptTemplateResponseWithCustomRenderer(@RequestBody PromptTemplateReq promptTemplateReq) {
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template("""
                You are <bot_name>, a customer support assistant for an e-commerce platform.
                
                Rules:
                - Be polite and professional
                - Help the customer clearly
                - Keep responses short and friendly
                - Apologize if there is an issue
                
                Customer Details:
                - Name: <name>
                - Order ID: <order_id>
                - Product: <product>
                - Issue: <issue>
                
                Support Email: <support_email>
                
                Respond politely and help the customer.
                """)
                .build();

        Map<String, Object> inputs = Map.of(
                "bot_name", "Alice",
                "name", promptTemplateReq.getCustomerName(),
                "order_id", promptTemplateReq.getOrderId(),
                "product", promptTemplateReq.getProduct(),
                "issue", promptTemplateReq.getIssue(),
                "support_email", "support@vengatstore.com"
        );

        Prompt prompt = promptTemplate.create(inputs);

        return chatClient.prompt(prompt).call().content();
    }

    @PostMapping("/prompttemplate/custom-template-file")
    public String getPromptTemplateResponseWithCustomTemplateFile(@RequestBody PromptTemplateReq promptTemplateReq) {
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemMessageResource);
        PromptTemplate userPromptTemplate = new PromptTemplate(userMessageResource);

        Map<String, Object> sysInputs = Map.of(
                "bot_name", "Alice",
                "support_email", "support@vengatstore.com"
        );
        Message systemMessage = systemPromptTemplate.createMessage(sysInputs);

        Map<String, Object> userInputs = Map.of(
                "name", promptTemplateReq.getCustomerName(),
                "order_id", promptTemplateReq.getOrderId(),
                "product", promptTemplateReq.getProduct(),
                "issue", promptTemplateReq.getIssue()
        );
        Message userMessage = userPromptTemplate.createMessage(userInputs);
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        return chatClient.prompt(prompt).call().content();
    }

}
