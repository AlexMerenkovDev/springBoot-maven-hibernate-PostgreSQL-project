package com.challenge.library.dao.impl;

import com.challenge.library.TestDataUtil;
import com.challenge.library.domain.Author;
import com.challenge.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    @Autowired
    private BookDAOImpl underTestBook;

    @Autowired
    private AuthorDAOImpl underTestAuthor;

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTestAuthor.create(author);

        Book book = TestDataUtil.createTestBookA();
        book.setAuthor_id(author.getId());
        underTestBook.create(book);

        Optional<Book> result = underTestBook.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
//  ********     Authors   ***********
        Author author = TestDataUtil.createTestAuthorA();
        underTestAuthor.create(author);
//
//        Author authorB = TestDataUtil.createTestAuthorB();
//        underTestAuthor.create(authorB);
//
//        Author authorC = TestDataUtil.createTestAuthorC();
//        underTestAuthor.create(authorC);

//   *********     Books   ***********
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthor_id(author.getId());
        underTestBook.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthor_id(author.getId());
        underTestBook.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthor_id(author.getId());
        underTestBook.create(bookC);

        List<Book> result = underTestBook.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }
}
