package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, UserEntity> users = new HashMap<>();

    @GetMapping
    public List<UserEntity> getAll() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public String createJournal(@RequestBody @Valid UserEntity user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        users.put(user.getId(), user);
        return "Entry Added successfully";
    }

    @GetMapping("/{id}")
    public UserEntity getJournalById(@PathVariable Long id) {
        UserEntity user = users.get(id);

        if(user == null)
            throw new RuntimeException("Journal with given id not found. id: " + id);
        return user;
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteJournalById(@PathVariable Long id) {

        UserEntity user = users.get(id);

        if(user == null)
            throw new RuntimeException("Journal with given id not found. id: " + id);
        users.remove(id);
        return user;
    }

    @PutMapping("/{id}")
    public UserEntity updateJournal(@PathVariable Long id, @RequestBody UserEntity updatedUser) {

        if(!users.containsKey(id))
            throw new RuntimeException("Journal with given id not found. id: " + id);
        updatedUser.setUpdatedAt(LocalDateTime.now());

        users.put(id, updatedUser);
        return updatedUser;
    }
}
