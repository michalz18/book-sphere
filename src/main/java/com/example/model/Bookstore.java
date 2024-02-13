package com.example.model;

import com.example.interfaces.Observer;
import com.example.interfaces.Subject;
import com.example.utils.OperationResult;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Getter
public class Bookstore implements Subject {
    private static final Logger logger = LoggerFactory.getLogger(Bookstore.class);
    private final Map<UUID, Book> books = new HashMap<>();
    private final Set<Category> categories = new HashSet<>();
    private final List<Observer> observers = new ArrayList<>();
    private final Set<Customer> customers = new HashSet<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public OperationResult addBook(Book book) {
        if (books.containsKey(book.getId())) {
            logger.warn("Attempted to add a book that already exists: {}", book);
            return operationFailed("Book already exists in the catalog.");
        }
        books.put(book.getId(), book);
        notifyObservers();
        logger.info("Book added: {}", book);
        return operationSuccess("Book successfully added to the catalog.");
    }

    public OperationResult removeBook(UUID bookId) {
        if (!books.containsKey(bookId)) {
            logger.warn("Attempted to remove non-existent book: {}", bookId);
            return operationFailed("Book does not exist in the catalog.");
        }
        books.remove(bookId);
        notifyObservers();
        logger.info("Book removed: {}", bookId);
        return operationSuccess("Book successfully removed from the catalog.");
    }

    public OperationResult editBook(Book updatedBook) {
        if (updatedBook == null || !books.containsKey(updatedBook.getId())) {
            return new OperationResult(false, "Book does not exist.");
        }
        books.put(updatedBook.getId(), updatedBook);
        notifyObservers();
        return new OperationResult(true, "Book updated successfully.");
    }

    public OperationResult addCategory(String categoryName) {
        Category newCategory = new Category(categoryName);
        if (!categories.add(newCategory)) {
            logger.warn("Attempted to add a category that already exists: {}", categoryName);
            return operationFailed("Category already exists.");
        }
        notifyObservers();
        logger.info("Category added: {}", newCategory);
        return operationSuccess("Category successfully added.");
    }

    public OperationResult addCustomer(Customer customer) {
        if (!customers.add(customer)) {
            logger.warn("Attempted to add a customer that already exists: {}", customer);
            return operationFailed("Customer already exists.");
        }
        logger.info("Customer added: {}", customer);
        return operationSuccess("Customer successfully added.");
    }

    public OperationResult sellBook(UUID bookId, Customer customer, int quantity) {
        Book book = books.get(bookId);
        if (book != null && book.getNumberOfCopiesAvailable() >= quantity) {
            book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - quantity);
            if (book.getNumberOfCopiesAvailable() == 0) {
                book.setAvailable(false);
            }
            logger.info("Book sold: {} to {} Quantity: {}", book, customer.getFirstName() + " " + customer.getLastName(), quantity);
            notifyObservers();
            return new OperationResult(true, "Book sold successfully. Quantity: " + quantity);
        }
        return new OperationResult(false, "Not enough copies available or book does not exist.");
    }

    private OperationResult operationSuccess(String message) {
        return new OperationResult(true, message);
    }

    private OperationResult operationFailed(String message) {
        return new OperationResult(false, message);
    }
}
