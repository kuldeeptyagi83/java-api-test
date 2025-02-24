package com.example.live.service;

import com.example.live.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalService {
    private final WebClient webClient;

    public ExternalService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<User> getAllExternalUsers() {
        try {
            User[] users = webClient.get()
                    .uri("http://wiremock:8080/client/users")
                    .retrieve()
                    .bodyToMono(User[].class)
                    .block();
            return Arrays.asList(users);
        } catch (Exception e) {
            // Handle exception (e.g., log it and return an empty list or a default response)
            System.out.println("Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}