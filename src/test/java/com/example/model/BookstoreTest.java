package com.example.model;

import com.example.view.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
    @Test
    void testRemoveBook() {
        bookstore.addBook(book);
        OperationResult removeResult = bookstore.removeBook(book.getId());
        assertTrue(removeResult.isSuccess());
        assertEquals("Book successfully removed from the catalog.", removeResult.getMessage());
    }

    @Test
    void testRemoveNonExistingBook() {
        OperationResult result = bookstore.removeBook(UUID.randomUUID());
        assertFalse(result.isSuccess());
        assertEquals("Book does not exist in the catalog.", result.getMessage());
    }

}