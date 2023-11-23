package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    List<Book> getAllBooks();

    List<Book> getAllBooksSortedByRatingDesc();

    void deleteBook(Long bookId);
    Book getBookById(Long bookId);
}
