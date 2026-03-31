package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImplementation userService;
//    UserServiceImplementation userService;

    @Test
    void shouldAddProductSuccessfully() {
        UserEntity user = new UserEntity();
        user.setFirstName("Ajay");

        when(userRepository.save(user)).thenReturn(user);

        UserEntity res = userService.createUser(user);

        assertNotNull(res);
        assertEquals("Ajay", res.getFirstName());
    }
}