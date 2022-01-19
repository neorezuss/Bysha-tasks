package com.example.task4.controller;

import com.example.task4.dto.ElixirDto;
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

import java.util.List;

import static com.example.task4.controller.ElixirRestControllerValidTest.asJsonString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("validUser")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/refresh-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CraftRestControllerValidTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void craftByIngredients() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/craft/ingredients")
                        .content(asJsonString(
                                List.of(
                                        new IngredientDto("Iron", IngredientType.SOLID, 100),
                                        new IngredientDto("Water", IngredientType.LIQUID, 100)
                                )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isCreated());
    }

    @Test
    void craftByRecipe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/craft/recipe")
                        .content(asJsonString(new ElixirDto("Speed elixir", 1000, 1)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isCreated());
    }
}