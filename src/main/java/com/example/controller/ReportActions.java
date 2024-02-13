package com.example.controller;

import com.example.model.*;
import com.example.model.reports.InventoryReport;
import com.example.model.reports.ReservationReport;
import com.example.model.reports.SalesReport;

import javax.swing.*;
import java.time.LocalDate;
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

    public List<ReservationReport> generateReservationReport(LocalDate startDate, LocalDate endDate) {
        return bookstore.getReservations().values().stream()
                .filter(reservation ->
                        !reservation.getReservationDate().isBefore(startDate.atStartOfDay())
                                && !reservation.getReservationDate().isAfter(endDate.atTime(23, 59)))
                .map(reservation ->
                        new ReservationReport
                                (
                                        reservation.getBook(),
                                        reservation.getCustomer(),
                                        reservation.getReservationDate(),
                                        reservation.getQuantity()))
                .toList();
    }

    public List<SalesReport> generateSalesReport(LocalDate startDate, LocalDate endDate) {
        return bookstore.getSales().values().stream()
                .filter(sale ->
                        !sale.getSaleDate().isBefore(startDate.atStartOfDay())
                                && !sale.getSaleDate().isAfter(endDate.atTime(23, 59)))
                .map(sale ->
                        new SalesReport
                                (
                                        sale.getBook(),
                                        sale.getCustomer(),
                                        sale.getSaleDate(),
                                        sale.getQuantitySold(),
                                        sale.getTotalPrice()))
                .toList();
    }

}
