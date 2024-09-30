# How to write an AI-powered REST API on Spring Boot

### About
This is a repository with a demo project for an article 
with a comprehensive guide. 

The demo project is illustrating, how you can use 
the Inference API of the Hugging Face platform, send requests 
and get responses with the HuggingFaceModelClient from any 
AI model, which is loaded on Inference API.

### Getting started
This section explains how to  use HuggingFaceModelClient in your Java projects.

#### Usage
 You can inject the client in your controller or service in the following way:

    private HuggingFaceModelClient client;

    @Autowired
    public ChatController() {
        this.client = HuggingFaceModelClient.builder()
                .modelName("google-t5/t5-small")
                .accessToken(ACCESS_TOKEN)
                .maxLength(100)
                .maxRetries(5)
                .retryDelay(1000)
                .build();
    }