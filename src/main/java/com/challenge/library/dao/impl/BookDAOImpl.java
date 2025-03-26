package com.challenge.library.dao.impl;

import com.challenge.library.dao.BookDAO;
import com.challenge.library.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        this.jdbcTemplate.update("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor_id());
    }
}
