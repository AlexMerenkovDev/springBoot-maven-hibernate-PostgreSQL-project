package com.challenge.library.dao.impl;

import com.challenge.library.TestDataUtil;
import com.challenge.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDAOImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql (){
        Book book = TestDataUtil.createTestBookA();

        this.underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("abcd"),
                eq("Jin Book"),
                eq(1L));
    }

    @Test
    public void testThatReadOneGeneratesCorrectSql() {
        this.underTest.findOne("516484");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any(),
                eq("516484")
                );
    }

    @Test
    public void testThatReadAllGeneratesCorrectSql(){
        this.underTest.findAll();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any()
        );
    }
}
