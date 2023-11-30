package com.example.demo.controller;

import com.example.demo.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddBook() throws Exception {
        // Arrange
        Book newBook = new Book("Test Book", 9);

        // Act (POST)
        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        Book addedBook = objectMapper.readValue(postResult.getResponse().getContentAsString(), Book.class);
        assertEquals(newBook.getTitle(), addedBook.getTitle());
        assertEquals(newBook.getRating(), addedBook.getRating());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Act (GET)
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = getResult.getResponse().getContentAsString();
        List<Book> books = objectMapper.readValue(content, List.class);

        // Check if the list of books is not empty
        assertFalse(books.isEmpty());
    }
}
