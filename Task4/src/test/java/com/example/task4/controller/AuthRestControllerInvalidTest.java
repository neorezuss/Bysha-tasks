package com.example.task4.controller;

import com.example.task4.dto.LoginDto;
import com.example.task4.dto.RegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.task4.controller.ElixirRestControllerValidTest.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/refresh-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AuthRestControllerInvalidTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void authenticateUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .content(asJsonString(new LoginDto("invalidUser", "invalidPassword")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void registerUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .content(asJsonString(new RegistrationDto("invalidUser", "invalidUser", "invalidUser")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}