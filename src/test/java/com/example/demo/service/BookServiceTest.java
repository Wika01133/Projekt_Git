package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void givenBookExists_whenDeleteBook_thenBookShouldBeDeleted() {

        Book bookToDelete = new Book("Spring in Action", 8);
        bookService.addBook(bookToDelete);
        bookService.deleteBook(bookToDelete.getId());
        assertNull(bookService.getBookById(bookToDelete.getId()));
    }
}

