package com.example.controller;

import com.example.model.Bookstore;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ReportActions {
    private final Bookstore bookstore;
    private final JFrame frame;
    private final Map<String, String> savedReports = new HashMap<>();

    public ReportActions(Bookstore bookstore, JFrame frame) {
        this.bookstore = bookstore;
        this.frame = frame;
    }

}
