package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Dodatkowe metody zapytań można umieścić tutaj, Spring Data JPA automatycznie dostarcza implementacje
    // dla podstawowych operacji CRUD (Create, Read, Update, Delete)
    List<Book> findAllByOrderByRatingDesc();
}
