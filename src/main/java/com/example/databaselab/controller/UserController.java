package com.example.databaselab.controller;

import com.example.databaselab.entity.User;
import com.example.databaselab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam UUID id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

}
