package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import org.h2.engine.User;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    void deleteUser(Long id);

    UserEntity updateUser(Long id, UserEntity user);

    List<UserEntity> getUsersbyFirstName(String name);

    List<UserEntity> getActiveUsers();
}