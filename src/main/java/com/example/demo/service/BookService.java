package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    List<Book> getAllBooks();
}
