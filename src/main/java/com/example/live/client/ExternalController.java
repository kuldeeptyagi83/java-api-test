package com.example.live.client;

import com.example.live.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
public class ExternalController {

    private final WebClient webClient;

    public ExternalController() {
        this.webClient = WebClient.builder().build();
    }

    @GetMapping("/client/users")
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
            System.out.println("Error: " + e.getMessage() );
            return Collections.emptyList();
        }
    }
}