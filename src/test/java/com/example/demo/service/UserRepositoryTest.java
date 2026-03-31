package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.util.Assert;


import java.util.List;

@DataJpaTest
class UserRepositoryTest  {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindActiveUsers() {
        UserEntity user = new UserEntity();
        user.setFirstName("Ajay");
        user.setLastName("Singh");
        user.setPhoneNumber("1234123412");
        user.setAddress("Bhpoal mp");
        user.setActive(true);

        userRepository.save(user);

        List<UserEntity> result = userRepository.findActiveUsers();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void shouldReturnAllUsers() {
        List<UserEntity> res = userRepository.findAll();
        Assertions.assertTrue(res.size() > 3);
    }

}
