package com.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreTest {

    private Bookstore bookstore;
    private Book book;

    @BeforeEach
    void setUp() {
        bookstore = new Bookstore();
        book = new Book("Test Book", "Author", 2020, 29.99, 10);
    }

    @Test
    void testAddBook() {
        OperationResult result = bookstore.addBook(book);
        assertTrue(result.isSuccess());
        assertEquals("Book successfully added to the catalog.", result.getMessage());
    }
    @Test
    void testAddTheSameBookTwice() {
        bookstore.addBook(book);
        OperationResult result = bookstore.addBook(book);
        assertFalse(result.isSuccess());
        assertEquals("Book already exists in the catalog.", result.getMessage());
    }

}