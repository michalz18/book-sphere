package com.example.model;

import com.example.view.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    public OperationResult editBook(
            UUID bookId,
            Optional<String> newTitle,
            Optional<String> newAuthor,
            Optional<Integer> newPublicationDate,
            Optional<Double> newPrice,
            Optional<Integer> newNumberOfCopiesAvailable
    ) {
        Book book = books.get(bookId);
        if (book == null) {
            return operationFailed("Book does not exist in the catalog.");
        }

        newTitle.ifPresent(book::setTitle);
        newAuthor.ifPresent(book::setAuthor);
        newPublicationDate.ifPresent(book::setPublicationDate);
        newPrice.ifPresent(book::setPrice);
        newNumberOfCopiesAvailable.ifPresent(n -> {
            book.setNumberOfCopiesAvailable(n);
            book.setAvailable(n > 0);
        });

        logger.info("Book updated: {}", book);
        return operationSuccess("Book successfully updated in the catalog.");
    }


    private OperationResult operationSuccess(String message) {
        return new OperationResult(true, message);
    }

    private OperationResult operationFailed(String message) {
        return new OperationResult(false, message);
    }
}
