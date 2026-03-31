package com.example.demo.service;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateUser() throws Exception {
        String json = """
    {
      "firstName": "Ajay",
      "lastName": "Singh",
      "address": "Bhopal MP",
      "phoneNumber": "1234567890",
      "emailAddress": "test@gmail.com",
      "active": true
    }
    """;

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailForInvalidEmail() throws Exception {
        String json = """
    {
      "firstName": "Ajay",
      "lastName": "Singh",
      "address": "Bhopal MP",
      "phoneNumber": "1234567890",
      "emailAddress": "invalid",
      "active": true
    }
    """;

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(json))
            .andExpect(status().isBadRequest());
    }
}