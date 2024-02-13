package com.example.controller;

import com.example.model.Book;
import com.example.model.Bookstore;
import com.example.model.Customer;
import com.example.view.components.BookDialog;
import com.example.utils.OperationResult;
import com.example.view.components.CustomerDialog;
import com.example.view.components.CustomerSelectionDialog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;

public class BookActions {
    private final Bookstore bookstore;
    private final JFrame frame;

    public BookActions(Bookstore bookstore, JFrame frame) {
        this.bookstore = bookstore;
        this.frame = frame;
    }

    public void addBook() {
        Book newBook = BookDialog.showDialog(frame, null, new ArrayList<>(bookstore.getCategories()));
        if (newBook != null) {
            OperationResult result = bookstore.addBook(newBook);
            JOptionPane.showMessageDialog(frame, result.message());
            bookstore.notifyObservers();
        }
    }

    public void editBook(UUID bookId) {
        Book bookToEdit = bookstore.getBooks().get(bookId);
        if (bookToEdit != null) {
            Book updatedBook = BookDialog.showDialog(frame, bookToEdit, new ArrayList<>(bookstore.getCategories()));
            if (updatedBook != null) {
                OperationResult result = bookstore.editBook(updatedBook);
                JOptionPane.showMessageDialog(frame, result.message());
                bookstore.notifyObservers();
            }
        }
    }

    public void removeBook(UUID bookId) {
        OperationResult result = bookstore.removeBook(bookId);
        JOptionPane.showMessageDialog(frame, result.message());
        bookstore.notifyObservers();
    }

    public void addCategory() {
        String categoryName = JOptionPane.showInputDialog(frame, "Enter category name:", "Add New Category", JOptionPane.PLAIN_MESSAGE);
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            OperationResult result = bookstore.addCategory(categoryName);
            JOptionPane.showMessageDialog(frame, result.message());
        }
    }
    public void sellBook(UUID bookId) {
        CustomerSelectionDialog customerSelectionDialog = new CustomerSelectionDialog(bookstore);
        Customer selectedCustomer = customerSelectionDialog.selectCustomer(frame);
        if (selectedCustomer != null) {
            Integer quantity = askForQuantity();
            if (quantity != null && quantity > 0) {
                OperationResult result = bookstore.sellBook(bookId, selectedCustomer, quantity);
                JOptionPane.showMessageDialog(frame, result.message());
            } else {
                JOptionPane.showMessageDialog(frame, "Sale cancelled, invalid quantity.", "Sale Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Sale cancelled, no customer selected.", "Sale Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void reserveBook(UUID bookId) {
        CustomerSelectionDialog customerSelectionDialog = new CustomerSelectionDialog(bookstore);
        Customer selectedCustomer = customerSelectionDialog.selectCustomer(frame);
        if (selectedCustomer != null) {
            Integer quantity = askForQuantity();
            if (quantity != null && quantity > 0) {
                OperationResult result = bookstore.reserveBook(bookId, selectedCustomer, quantity);
                JOptionPane.showMessageDialog(frame, result.message());
            } else {
                JOptionPane.showMessageDialog(frame, "Reservation cancelled, invalid quantity.", "Reservation Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Reservation cancelled, no customer selected.", "Reservation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Integer askForQuantity() {
        String quantityString = JOptionPane.showInputDialog(frame, "Enter quantity:", "Quantity", JOptionPane.PLAIN_MESSAGE);
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid quantity entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
