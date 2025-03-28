package com.challenge.library;

import com.challenge.library.domain.Author;
import com.challenge.library.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Alex Merenkovas")
                .age(27)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Amelia Langas")
                .age(25)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Belmas Macome")
                .age(40)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("abcd")
                .title("Jin Book")
                .author_id(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("4155")
                .title("Kizama Book")
                .author_id(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("0215")
                .title("Brother Book")
                .author_id(3L)
                .build();
    }
}
