package com.example.controller;

import com.example.model.Book;
import com.example.model.Bookstore;
import com.example.model.InventoryReport;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportActions {
    private final Bookstore bookstore;
    private final JFrame frame;
    private final Map<String, String> savedReports = new HashMap<>();

    public ReportActions(Bookstore bookstore, JFrame frame) {
        this.bookstore = bookstore;
        this.frame = frame;
    }

    public List<InventoryReport> generateInventoryReport() {
        List<InventoryReport> reports = new ArrayList<>();
        for (Book book : bookstore.getBooks().values()) {
            InventoryReport reportItem = new InventoryReport(book, book.getNumberOfCopiesAvailable());
            reports.add(reportItem);
        }
        return reports;
    }

}
