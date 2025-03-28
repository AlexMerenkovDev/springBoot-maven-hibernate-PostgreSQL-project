package com.challenge.library.dao.impl;

import com.challenge.library.dao.AuthorDAO;
import com.challenge.library.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDAOImpl implements AuthorDAO {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        this.jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(),
                author.getName(),
                author.getAge());
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                authorId);

        return results.stream().findFirst();
    }

    @Override
    public List<Author> findAll() {
        List<Author> result = jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
        );
        return result;
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
