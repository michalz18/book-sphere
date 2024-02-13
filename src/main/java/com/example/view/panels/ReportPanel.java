package com.example.view.panels;

import com.example.controller.ReportActions;
import com.example.model.Book;
import com.example.model.reports.InventoryReport;
import com.example.model.reports.ReservationReport;
import com.example.model.reports.SalesReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.time.LocalDate;
import java.util.Vector;

public class ReportPanel extends JPanel {
    private final ReportActions reportActions;
    private JTable reportTable;
    private final JFrame frame;

    private DefaultTableModel tableModel;

    public ReportPanel(ReportActions reportActions, JFrame frame) {
        this.reportActions = reportActions;
        this.frame = frame;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        add(createControlPanel(), BorderLayout.NORTH);
        add(new JScrollPane(reportTable), BorderLayout.CENTER);
    }

    private void generateInventoryReport() {
        List<InventoryReport> reports = reportActions.generateInventoryReport();
        Vector<String> columnNames = new Vector<>(List.of("Book ID", "Title", "Author", "Available Copies"));
        Vector<Vector<Object>> data = new Vector<>();
        for (InventoryReport report : reports) {
            Vector<Object> row = new Vector<>();
            Book book = report.getBook();
            row.add(book.getId());
            row.add(book.getTitle());
            row.add(book.getAuthor());
            row.add(report.getAvailableCopies());
            data.add(row);
        }
        tableModel.setDataVector(data, columnNames);
    }

    private void generateReservationReport(LocalDate startDate, LocalDate endDate) {
        List<ReservationReport> reports = reportActions.generateReservationReport(startDate, endDate);
        Vector<String> columnNames = new Vector<>(List.of("Book ID", "Title", "Customer Name", "Reservation Date", "Quantity"));
        Vector<Vector<Object>> data = new Vector<>();
        for (ReservationReport report : reports) {
            Vector<Object> row = new Vector<>();
            row.add(report.getBook().getId().toString());
            row.add(report.getBook().getTitle());
            row.add(report.getCustomer().getFullName());
            row.add(report.getReservationDate().toString());
            row.add(report.getQuantityReserved());
            data.add(row);
        }
        tableModel.setDataVector(data, columnNames);
    }


    private void generateSalesReport(LocalDate startDate, LocalDate endDate) {
        List<SalesReport> reports = reportActions.generateSalesReport(startDate, endDate);
        Vector<String> columnNames = new Vector<>(List.of("Book ID", "Title", "Customer Name", "Sale Date", "Quantity Sold", "Total Price"));
        Vector<Vector<Object>> data = new Vector<>();
        for (SalesReport report : reports) {
            Vector<Object> row = new Vector<>();
            row.add(report.getBook().getId().toString());
            row.add(report.getBook().getTitle());
            row.add(report.getCustomer().getFirstName() + " " + report.getCustomer().getLastName());
            row.add(report.getSaleDate().toString());
            row.add(report.getQuantitySold());
            row.add(String.format("%.2f", report.getTotalSaleAmount()));
            data.add(row);
        }
        tableModel.setDataVector(data, columnNames);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton inventoryReportButton = new JButton("Inventory Report");
        JButton reservationReportButton = new JButton("Reservation Report");
        JButton salesReportButton = new JButton("Sales Report");

        inventoryReportButton.addActionListener(e -> generateInventoryReport());

        reservationReportButton.addActionListener(e -> {
            LocalDate startDate = promptForDate("Enter start date (YYYY-MM-DD):");
            LocalDate endDate = promptForDate("Enter end date (YYYY-MM-DD):");
            if (startDate != null && endDate != null) {
                generateReservationReport(startDate, endDate);
            }
        });

        salesReportButton.addActionListener(e -> {
            LocalDate startDate = promptForDate("Enter start date (YYYY-MM-DD):");
            LocalDate endDate = promptForDate("Enter end date (YYYY-MM-DD):");
            if (startDate != null && endDate != null) {
                generateSalesReport(startDate, endDate);
            }
        });

        panel.add(inventoryReportButton);
        panel.add(reservationReportButton);
        panel.add(salesReportButton);

        return panel;
    }
    private LocalDate promptForDate(String message) {
        String dateString = JOptionPane.showInputDialog(frame, message);
        try {
            return LocalDate.parse(dateString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
