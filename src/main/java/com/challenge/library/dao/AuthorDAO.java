package com.challenge.library.dao;

import com.challenge.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> findAll();
}
