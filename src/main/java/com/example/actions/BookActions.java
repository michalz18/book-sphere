package com.example.actions;

import com.example.model.Book;
import com.example.model.Bookstore;
import com.example.view.BookDialog;
import com.example.view.OperationResult;

import javax.swing.*;
import java.util.UUID;

public class BookActions {

    private final Bookstore bookstore;
    private final JFrame frame;

    public BookActions(Bookstore bookstore, JFrame frame) {
        this.bookstore = bookstore;
        this.frame = frame;
    }

    public void addBook() {
        Book newBook = BookDialog.showDialog(frame, null);
        if (newBook != null) {
            OperationResult result = bookstore.addBook(newBook);
            JOptionPane.showMessageDialog(frame, result.getMessage());
        }
    }

    public void editBook(UUID bookId) {
        Book bookToEdit = bookstore.getBooks().get(bookId);
        if (bookToEdit != null) {
            Book updatedBook = BookDialog.showDialog(frame, bookToEdit);
            if (updatedBook != null) {
                OperationResult result = bookstore.editBook(updatedBook);
                JOptionPane.showMessageDialog(frame, result.isSuccess() ? "Book successfully updated." : result.getMessage());
            }
        }
    }

    public void removeBook(UUID bookId) {
        OperationResult result = bookstore.removeBook(bookId);
        JOptionPane.showMessageDialog(frame, result.isSuccess() ? "Book successfully removed." : result.getMessage());
    }
}
