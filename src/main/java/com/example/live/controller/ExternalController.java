package com.example.live.controller;

import com.example.live.model.User;
import com.example.live.service.ExternalService;
import com.example.live.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExternalController {
    private final ExternalService externalService;

    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/client/users")
    public List<User> getAllExternalUsers() {
        return externalService.getAllExternalUsers();
    }
}