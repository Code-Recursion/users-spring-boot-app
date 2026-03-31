package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserEntity createJournal(@RequestBody @Valid UserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping("/name/{name}")
    public List<UserEntity> getUserByFName(@PathVariable String name) {
        return userService.getUsersbyFirstName(name);
    }

    @GetMapping("/active")
    public List<UserEntity> getActiveUsers() {
        return userService.getActiveUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getJournalById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJournalById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateJournal(@PathVariable Long id, @RequestBody UserEntity user) {
       return userService.updateUser(id, user);
    }
}
