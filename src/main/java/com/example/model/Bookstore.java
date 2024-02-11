package com.example.model;

import com.example.view.OperationResult;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public class Bookstore {
    private static final Logger logger = LoggerFactory.getLogger(Bookstore.class);
    private final Map<UUID, Book> books = new HashMap<>();

    public OperationResult addBook(Book book) {
        if (books.containsKey(book.getId())) {
            logger.warn("Attempted to add a book that already exists: {}", book);
            return operationFailed("Book already exists in the catalog.");
        }
        books.put(book.getId(), book);
        logger.info("Book added: {}", book);
        return operationSuccess("Book successfully added to the catalog.");
    }

    public OperationResult removeBook(UUID bookId) {
        if (!books.containsKey(bookId)) {
            logger.warn("Attempted to remove non-existent book: {}", bookId);
            return operationFailed("Book does not exist in the catalog.");
        }
        books.remove(bookId);
        logger.info("Book removed: {}", bookId);
        return operationSuccess("Book successfully removed from the catalog.");
    }

    public OperationResult editBook(Book updatedBook) {
        if (updatedBook == null || !books.containsKey(updatedBook.getId())) {
            return new OperationResult(false, "Book does not exist.");
        }
        books.put(updatedBook.getId(), updatedBook);
        return new OperationResult(true, "Book updated successfully.");
    }




    private OperationResult operationSuccess(String message) {
        return new OperationResult(true, message);
    }

    private OperationResult operationFailed(String message) {
        return new OperationResult(false, message);
    }
}
