package com.example.model;

import com.example.view.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreTest {

    private Bookstore bookstore;
    private Book book;

    @BeforeEach
    void setUp() {
        bookstore = new Bookstore();
        book = new Book(UUID.randomUUID(), "Existing Book", "Existing Author", 2000, 15.99, 5, true);
        OperationResult addResult = bookstore.addBook(book);
        assertTrue(addResult.isSuccess());
    }

    @Test
    void testAddTheSameBookTwice() {
        OperationResult result = bookstore.addBook(book);
        assertFalse(result.isSuccess());
        assertEquals("Book already exists in the catalog.", result.getMessage());
    }

    @Test
    void testRemoveBook() {
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

    @Test
    void editExistingBook() {
        OperationResult result = bookstore.editBook(book.getId(), Optional.of("Updated Title"), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        assertTrue(result.isSuccess());
        assertEquals("Book successfully updated in the catalog.", result.getMessage());

        Book updatedBook = bookstore.getBooks().get(book.getId());
        assertNotNull(updatedBook);
        assertEquals("Updated Title", updatedBook.getTitle());
    }

    @Test
    void editNonExistingBookFailure() {
        OperationResult result = bookstore.editBook(UUID.randomUUID(), Optional.of("Non-Existing Title"), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        assertFalse(result.isSuccess());
        assertEquals("Book does not exist in the catalog.", result.getMessage());
    }

    @Test
    void editBookPriceOnly() {
        double newPrice = 25.99;
        OperationResult result = bookstore.editBook(book.getId(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(newPrice), Optional.empty());

        assertTrue(result.isSuccess());
        assertEquals("Book successfully updated in the catalog.", result.getMessage());

        Book updatedBook = bookstore.getBooks().get(book.getId());
        assertNotNull(updatedBook);
        assertEquals(newPrice, updatedBook.getPrice());
    }
}