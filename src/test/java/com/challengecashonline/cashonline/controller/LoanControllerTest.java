package com.challengecashonline.cashonline.controller;

import com.challengecashonline.cashonline.service.LoanService;
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
class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanService loanService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }


    @Test
    void createLoan() throws Exception {
        String requestJson = "{\n" +
                "  \"total\": 1000.0,\n" +
                "  \"user\": 1\n" +
                "}";

        this.mockMvc.perform(post("/loan/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void getLoanById() throws Exception{
        Long id = 1L;

        this.mockMvc.perform(get("/loan/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateLoan() throws Exception{
        Long id = 1L;
        String requestJson = "{\n" +
                "  \"total\": 1000.0,\n" +
                "  \"user\": 1\n" +
                "}";

        this.mockMvc.perform(put("/loan/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLoans() throws Exception{
    }

    @Test
    void deleteLoan() throws Exception{
        Long id = 1L;

        this.mockMvc.perform(delete("/loan/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}