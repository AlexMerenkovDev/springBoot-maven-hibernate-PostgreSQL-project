package com.challenge.library.dao.impl;

import com.challenge.library.TestDataUtil;
import com.challenge.library.domain.Author;
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
public class AuthorDaoImplIntegrationTests {

    @Autowired
    private AuthorDAOImpl underTest;

//    public AuthorDaoImplIntegrationTests (AuthorDAOImpl underTest) {
//        this.underTest = underTest;
//    }


    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){

        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);

        List<Author> results = underTest.findAll();
        assertThat(results)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

}
