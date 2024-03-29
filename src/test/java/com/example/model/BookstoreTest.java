//package com.example.model;
//
//import com.example.utils.OperationResult;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BookstoreTest {
//
//    private Bookstore bookstore;
//    private Book book;
//
//    @BeforeEach
//    void setUp() {
//        bookstore = new Bookstore();
//        book = new Book(UUID.randomUUID(), "Existing Book", "Existing Author", 2000, 15.99, 5, true);
//        bookstore.addBook(book);
//    }
//
//    @Test
//    void testAddTheSameBookTwice() {
//        OperationResult result = bookstore.addBook(book);
//        assertFalse(result.success());
//        assertEquals("Book already exists in the catalog.", result.message());
//    }
//
//    @Test
//    void testRemoveBook() {
//        OperationResult removeResult = bookstore.removeBook(book.getId());
//        assertTrue(removeResult.success());
//        assertEquals("Book successfully removed from the catalog.", removeResult.message());
//    }
//
//    @Test
//    void testRemoveNonExistingBook() {
//        OperationResult result = bookstore.removeBook(UUID.randomUUID());
//        assertFalse(result.success());
//        assertEquals("Book does not exist in the catalog.", result.message());
//    }
//
//    @Test
//    void editExistingBook() {
//        Book updatedBook = new Book(book.getId(), "Updated Title", book.getAuthor(), book.getPublicationDate(), book.getPrice(), book.getNumberOfCopiesAvailable(), book.isAvailable());
//        OperationResult result = bookstore.editBook(updatedBook);
//        assertTrue(result.success());
//        assertEquals("Book successfully updated in the catalog.", result.message());
//
//        Book retrievedBook = bookstore.getBooks().get(book.getId());
//        assertNotNull(retrievedBook);
//        assertEquals("Updated Title", retrievedBook.getTitle());
//    }
//
//    @Test
//    void editNonExistingBookFailure() {
//        Book nonExistingBook = new Book(UUID.randomUUID(), "Non-Existing Title", "Non-Existing Author", 2021, 20.99, 10, true);
//        OperationResult result = bookstore.editBook(nonExistingBook);
//        assertFalse(result.success());
//        assertEquals("Book does not exist in the catalog.", result.message());
//    }
//
//    @Test
//    void editBookPriceOnly() {
//        double newPrice = 25.99;
//        Book updatedBook = new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationDate(), newPrice, book.getNumberOfCopiesAvailable(), book.isAvailable());
//        OperationResult result = bookstore.editBook(updatedBook);
//
//        assertTrue(result.success());
//        assertEquals("Book successfully updated in the catalog.", result.message());
//
//        Book retrievedBook = bookstore.getBooks().get(book.getId());
//        assertNotNull(retrievedBook);
//        assertEquals(newPrice, retrievedBook.getPrice());
//    }
//}
