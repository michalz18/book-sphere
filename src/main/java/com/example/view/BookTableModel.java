package com.example.view;

import com.example.model.Book;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID",
            "Title",
            "Author",
            "Year",
            "Price",
            "Copies",
            "Reserved Copies",
            "Category",
            "Edit",
            "Remove",
            "Sell",
            "Reserve"
    };
    private List<Book> books;

    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> book.getId().toString();
            case 1 -> book.getTitle();
            case 2 -> book.getAuthor();
            case 3 -> book.getPublicationDate();
            case 4 -> book.getPrice();
            case 5 -> book.getNumberOfCopiesAvailable();
            case 6 -> book.getNumberOfReservations();
            case 7 -> book.getCategory() != null ? book.getCategory().getName() : "N/A";
            case 8 -> "Edit";
            case 9 -> "Remove";
            case 10 -> "Sell";
            case 11 -> "Reserve";
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 8;
    }
}
