package com.example.model;

import com.example.interfaces.Observer;
import com.example.interfaces.Subject;
import com.example.utils.OperationResult;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Bookstore implements Subject {
    private static final Logger logger = LoggerFactory.getLogger(Bookstore.class);
    private final Map<UUID, Book> books = new HashMap<>();
    private final Set<Category> categories = new HashSet<>();
    private final List<Observer> observers = new ArrayList<>();
    private final Set<Customer> customers = new HashSet<>();
    private final Map<UUID, Sale> sales = new HashMap<>();
    private final Map<UUID, Reservation> reservations = new HashMap<>();

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
        if (book == null) {
            return operationFailed("Book does not exist.");
        }
        if (quantity <= 0 || quantity > (book.getNumberOfCopiesAvailable() - book.getNumberOfReservations())) {
            return operationFailed("Not enough unreserved copies available for sale.");
        }

        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - quantity);

        double totalPrice = quantity * book.getPrice();

        Sale sale = new Sale(book, customer, quantity, totalPrice);
        sales.put(sale.getId(), sale);

        if (book.getNumberOfCopiesAvailable() <= 0) {
            book.setAvailable(false);
        }

        logger.info("Book sold: {} to {} Quantity: {}, Total Price: {}", book.getTitle(), customer.getFirstName() + " " + customer.getLastName(), quantity, totalPrice);
        notifyObservers();
        return operationSuccess("Book sold successfully. Quantity: " + quantity + ", Total Price: " + totalPrice);
    }

    public OperationResult reserveBook(UUID bookId, Customer customer, int quantity) {
        Book book = books.get(bookId);
        if (book == null) {
            return operationFailed("Book does not exist.");
        }
        if (quantity <= 0) {
            return operationFailed("Quantity must be greater than zero.");
        }
        int availableForReservation = book.getNumberOfCopiesAvailable() - book.getNumberOfReservations();
        if (quantity > availableForReservation) {
            return operationFailed("Invalid quantity for reservation. Only " + availableForReservation + " available.");
        }

        Reservation reservation = new Reservation(book, customer, quantity);
        reservations.put(reservation.getId(), reservation);

        book.setNumberOfReservations(book.getNumberOfReservations() + quantity);
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - quantity);
        if (book.getNumberOfCopiesAvailable() == 0) {
            book.setAvailable(false);
        }
        logger.info("Book reserved: {} by {} Quantity: {}", book.getTitle(), customer.getFirstName() + " " + customer.getLastName(), quantity);
        notifyObservers();
        return operationSuccess("Book reserved successfully. Quantity: " + quantity);
    }

    public List<InventoryReport> generateInventoryReport() {
        if (books.isEmpty()) {
            logger.info("Generating inventory report: No books found in the inventory.");
            return Collections.emptyList();
        }
        List<InventoryReport> report = new ArrayList<>();
        for (Book book : books.values()) {
            report.add(new InventoryReport(book, book.getNumberOfCopiesAvailable()));
        }
        logger.info("Inventory report generated successfully: {} books reported.", report.size());
        return report;
    }

    public List<ReservationReport> generateReservationReport(LocalDateTime startDate, LocalDateTime endDate) {
        if (reservations.isEmpty()) {
            logger.info("Generating reservation report: No reservations found.");
            return Collections.emptyList();
        }
        List<ReservationReport> report = reservations.values().stream()
                .filter(reservation -> !reservation.getReservationDate().isBefore(startDate) && !reservation.getReservationDate().isAfter(endDate))
                .map(reservation -> new ReservationReport(reservation.getBook(), reservation.getCustomer(), reservation.getReservationDate(), reservation.getQuantity()))
                .toList();

        if (report.isEmpty()) {
            logger.info("No reservations found within the specified date range: {} to {}.", startDate, endDate);
        } else {
            logger.info("Reservation report generated successfully: {} reservations reported within the date range {} to {}.", report.size(), startDate, endDate);
        }
        return report;
    }


    private OperationResult operationSuccess(String message) {
        return new OperationResult(true, message);
    }

    private OperationResult operationFailed(String message) {
        return new OperationResult(false, message);
    }
}
