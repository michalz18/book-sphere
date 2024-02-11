package com.example.view.components;

import com.example.model.Book;
import com.example.model.Category;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class BookDialog {

    public static Book showDialog(Component parent, Book book, List<Category> categories) {
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField yearField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextField copiesField = new JTextField(20);
        JComboBox<Category> categoryComboBox = new JComboBox<>(categories.toArray(new Category[0]));

        if (book != null) {
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            yearField.setText(String.valueOf(book.getPublicationDate()));
            priceField.setText(String.valueOf(book.getPrice()));
            copiesField.setText(String.valueOf(book.getNumberOfCopiesAvailable()));
            categoryComboBox.setSelectedItem(book.getCategory());
        }

        Object[] message = {
                "Title:", titleField,
                "Author:", authorField,
                "Year:", yearField,
                "Price:", priceField,
                "Copies:", copiesField,
                "Category:", categoryComboBox
        };

        int option = JOptionPane.showConfirmDialog(parent, message, book == null ? "Add New Book" : "Edit Book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());
                double price = Double.parseDouble(priceField.getText());
                int copies = Integer.parseInt(copiesField.getText());
                Category selectedCategory = (Category) categoryComboBox.getSelectedItem();

                if (book == null) {
                    book = new Book(UUID.randomUUID(), title, author, year, price, copies, copies > 0, selectedCategory);
                } else {
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setPublicationDate(year);
                    book.setPrice(price);
                    book.setNumberOfCopiesAvailable(copies);
                    book.setAvailable(copies > 0);
                    book.setCategory(selectedCategory);
                }
                return book;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Please enter valid numbers for year, price, and copies.");
            }
        }
        return null;
    }
}
