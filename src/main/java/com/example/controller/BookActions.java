package com.example.controller;

import com.example.model.Book;
import com.example.model.Bookstore;
import com.example.view.components.BookDialog;
import com.example.utils.OperationResult;

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
        OperationResult result = bookstore.sellBook(bookId);
        JOptionPane.showMessageDialog(frame, result.message());
        bookstore.notifyObservers();
    }
}
