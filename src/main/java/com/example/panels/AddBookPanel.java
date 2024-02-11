package com.example.panels;

import com.example.actions.BookActions;
import com.example.model.Book;

import javax.swing.*;
import java.awt.*;

public class AddBookPanel extends JPanel {
    private final BookActions bookActions;

    public AddBookPanel(BookActions bookActions) {
        this.bookActions = bookActions;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new FlowLayout());

        JButton addButton = new JButton("Add New Book");
        addButton.addActionListener(e -> bookActions.addBook());
        add(addButton);
    }
}
