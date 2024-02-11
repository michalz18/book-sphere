package com.example.view.panels;

import com.example.controller.BookActions;
import com.example.interfaces.Observer;
import com.example.model.Bookstore;
import com.example.view.BookTableModel;
import com.example.view.components.ButtonEditor;
import com.example.view.components.ButtonRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class ManageBooksPanel extends JPanel implements Observer {
    private final Bookstore bookstore; // Dodano pole Bookstore
    private final JTable booksTable;
    private final BookTableModel bookTableModel;
    private final BookActions bookActions;

    public ManageBooksPanel(Bookstore bookstore, BookActions bookActions) {
        this.bookstore = bookstore;
        this.bookActions = bookActions;
        bookstore.attach(this);
        setLayout(new BorderLayout());
        bookTableModel = new BookTableModel(new java.util.ArrayList<>(bookstore.getBooks().values()));
        booksTable = new JTable(bookTableModel);
        setupTableButtons();
        add(new JScrollPane(booksTable), BorderLayout.CENTER);
    }

    private void setupTableButtons() {
        booksTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        booksTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), row -> bookActions.editBook(getBookId(row))));

        booksTable.getColumn("Remove").setCellRenderer(new ButtonRenderer());
        booksTable.getColumn("Remove").setCellEditor(new ButtonEditor(new JCheckBox(), row -> bookActions.removeBook(getBookId(row))));
    }

    private UUID getBookId(int row) {
        return UUID.fromString(bookTableModel.getValueAt(row, 0).toString());
    }

    @Override
    public void update() {
        refreshBooksTable();
    }

    public void refreshBooksTable() {
        bookTableModel.setBooks(new ArrayList<>(bookstore.getBooks().values()));
        bookTableModel.fireTableDataChanged();
    }
}
