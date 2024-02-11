package com.example.view.components;

import com.example.model.Book;
import com.example.model.Category;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookDialog {

    public static Book showDialog(Component parent, Book book, List<Category> categories) {
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField yearField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextField copiesField = new JTextField(20);

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getName(), category);
        }

        JComboBox<String> categoryComboBox = new JComboBox<>(categoryMap.keySet().toArray(new String[0]));

        if (book != null && book.getCategory() != null) {
            categoryComboBox.setSelectedItem(book.getCategory().getName());
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

                String selectedCategoryName = (String) categoryComboBox.getSelectedItem();
                Category selectedCategory = categoryMap.get(selectedCategoryName);

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
