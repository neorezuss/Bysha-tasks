package com.example.task4.controller;

import com.example.task4.dto.IngredientDto;
import com.example.task4.entity.IngredientType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static com.example.task4.controller.ElixirRestControllerValidTest.asJsonString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("validUser")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/refresh-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class IngredientRestControllerValidTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserIngredients() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ingredients")
                        .content(asJsonString(new HashMap<>()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }

    @Test
    void buyIngredient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/ingredients")
                        .content(asJsonString(new IngredientDto("Blueberry", IngredientType.HERB, 100)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }

    @Test
    void sellIngredient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ingredients")
                        .content(asJsonString(new IngredientDto("Blueberry", IngredientType.HERB, 100)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }
}