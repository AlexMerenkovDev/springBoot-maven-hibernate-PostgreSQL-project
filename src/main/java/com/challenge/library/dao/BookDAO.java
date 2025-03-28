package com.challenge.library.dao;

import com.challenge.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();
}
