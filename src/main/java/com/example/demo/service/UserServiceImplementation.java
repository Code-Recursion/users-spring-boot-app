package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User Not found"
                ));
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User Not found"
            );
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for id: " + id);
        }
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getUsersbyFirstName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public List<UserEntity> getActiveUsers() {
        return userRepository.findActiveUsers();
    }
}
