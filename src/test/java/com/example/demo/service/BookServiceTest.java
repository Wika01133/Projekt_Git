package com.example.demo.service;

import com.example.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void givenBookExists_whenDeleteBook_thenBookShouldBeDeleted() {
        // Arrange
        Book bookToDelete = new Book("Spring in Action", 8);
        bookService.addBook(bookToDelete);

        // Act
        bookService.deleteBook(bookToDelete.getId());

        // Assert
        assertEquals(null, bookService.getBookById(bookToDelete.getId()));
    }

    @Test
    public void givenBooksExist_whenGetAllBooksSortedByRating_thenBooksShouldBeSorted() {
        // Pobierz istniejące książki z bazy danych
        List<Book> existingBooks = bookService.getAllBooks();

        // Sprawdź, czy istnieją jakiekolwiek książki w bazie danych
        assertTrue(existingBooks.size() > 0);

        // Pobierz książki posortowane po ocenie
        List<Book> sortedBooks = bookService.getAllBooksSortedByRatingDesc();

        // Sprawdź, czy ilość książek się zgadza
        assertEquals(existingBooks.size(), sortedBooks.size());

        // Sprawdź, czy książki są posortowane malejąco po ocenie
        for (int i = 0; i < sortedBooks.size() - 1; i++) {
            assertTrue(sortedBooks.get(i).getRating() >= sortedBooks.get(i + 1).getRating());
        }
    }
}
