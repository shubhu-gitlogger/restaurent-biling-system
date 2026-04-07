package com.hotelbilingsystem.backend.service;

import com.hotelbilingsystem.backend.model.User;
import com.hotelbilingsystem.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register Admin
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

    // Login
    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() &&
                passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }

        return Optional.empty();
    }
}