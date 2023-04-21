package com.challengecashonline.cashonline.controller;

import com.challengecashonline.cashonline.config.ControllerAdviceConfig;
import com.challengecashonline.cashonline.model.mapper.UserMapper;
import com.challengecashonline.cashonline.repository.UserRepository;
import com.challengecashonline.cashonline.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new ControllerAdviceConfig()).build();
    }

    @Test
    void createUser() throws Exception {

        String requestJson = "{\n" +
                "  \"firstName\": \"Juan\",\n" +
                "  \"lastName\": \"Vazquez\",\n" +
                "  \"email\": \"juan@gmail.com\"\n" +
                "}";

        this.mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUser() throws Exception {
        this.mockMvc.perform(get("/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        Long id = 1L;

        this.mockMvc.perform(get("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        Long id = 1L;
        String requestJson = "{\n" +
                "  \"firstName\": \"Juan\",\n" +
                "  \"lastName\": \"Vazquez\",\n" +
                "  \"email\": \"juan@gmail.com\"\n" +
                "}";

        this.mockMvc.perform(put("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

    }

    @Test
    void getUserByFirstName() throws Exception {
    }

    @Test
    void deleteUser() throws Exception {
        Long id = 1L;

        this.mockMvc.perform(delete("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}