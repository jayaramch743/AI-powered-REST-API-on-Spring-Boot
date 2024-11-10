package com.hackernoon.aidemo.controller;

import com.hackernoon.aidemo.client.HuggingFaceModelClient;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class ChatController {
    private final String ACCESS_TOKEN;
    private HuggingFaceModelClient client;

    @Autowired
    public ChatController() {
        // Load the environment variables from the .env file
        Dotenv dotenv = Dotenv.load();
        this.ACCESS_TOKEN = dotenv.get("HUGGINGFACE_ACCESS_TOKEN");

        // Initialise the HuggingFaceModelClient with the access token from .env
        this.client = HuggingFaceModelClient.builder()
                .modelName("google-t5/t5-small")
                .accessToken(ACCESS_TOKEN)
                .maxLength(100)
                .maxRetries(5)
                .retryDelay(1000)
                .build();
    }

    @GetMapping("/generate")
    public Map<String, Object> generate(@RequestParam(value = "message", defaultValue = "I love coffee") String message) throws IOException {
        return Map.of("generation", client.call(message));
    }
}
