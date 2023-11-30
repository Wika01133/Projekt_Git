package com.example.demo.service;

import com.example.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void givenBookExists_whenDeleteBook_thenBookShouldBeDeleted() {

        Book bookToDelete = new Book("Spring in Action", 8);
        bookService.addBook(bookToDelete);


        bookService.deleteBook(bookToDelete.getId());


        assertEquals(null, bookService.getBookById(bookToDelete.getId()));
    }

    @Test
    public void givenBooksExist_whenGetAllBooksSortedByRating_thenBooksShouldBeSorted() {


        List<Book> existingBooks = bookService.getAllBooks();


        assertTrue(existingBooks.size() > 0);


        List<Book> sortedBooks = bookService.getAllBooksSortedByRatingDesc();


        assertEquals(existingBooks.size(), sortedBooks.size());


        for (int i = 0; i < sortedBooks.size() - 1; i++) {
            assertTrue(sortedBooks.get(i).getRating() >= sortedBooks.get(i + 1).getRating());
        }
    }
}
