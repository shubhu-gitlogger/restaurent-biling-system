package com.hotelbilingsystem.backend.controller;

import com.hotelbilingsystem.backend.dto.LoginRequest;
import com.hotelbilingsystem.backend.dto.RegisterRequest;
import com.hotelbilingsystem.backend.model.User;
import com.hotelbilingsystem.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register Admin
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return ResponseEntity.ok(userService.register(user));
    }

    // Login Admin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> user =
                userService.login(request.getUsername(), request.getPassword());

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}