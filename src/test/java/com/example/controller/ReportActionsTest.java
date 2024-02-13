package com.example.controller;

import com.example.controller.ReportActions;
import com.example.model.*;
import com.example.model.reports.InventoryReport;
import com.example.model.reports.ReservationReport;
import com.example.model.reports.SalesReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class ReportActionsTest {

    private Bookstore bookstore;
    private ReportActions reportActions;

    @BeforeEach
    void setUp() {
        bookstore = Mockito.mock(Bookstore.class);
        reportActions = new ReportActions(bookstore, null);
    }

    @Test
    void testGenerateInventoryReport() {
        Book testBook = new Book("Test Book", "Author", 2020, 20.0, 5, new Category("Fiction"));
        Mockito.when(bookstore.getBooks()).thenReturn(Collections.singletonMap(testBook.getId(), testBook));

        List<InventoryReport> reports = reportActions.generateInventoryReport();

        assertEquals(1, reports.size(), "There should be one report item.");

        InventoryReport report = reports.get(0);
        assertEquals(testBook.getId(), report.getBook().getId(), "The book ID should match the test book's ID.");
        assertEquals("Test Book", report.getBook().getTitle(), "The book title should match 'Test Book'.");
        assertEquals(5, report.getAvailableCopies(), "The available copies should be 5.");
    }


    @Test
    void testGenerateReservationReport() {
        Book book = new Book(UUID.randomUUID(), "Sample Book", "Author Name", 2021, 25.99, 5, 0, true, new Category("Genre"));
        Customer customer = new Customer("John", "Doe", "City", "Street", "12345", "123123123", "email@example.com");

        Reservation reservation = new Reservation(book, customer, 2);
        reservation.setReservationDate(LocalDateTime.of(2021, 12, 15, 12, 0));

        Map<UUID, Reservation> reservations = new HashMap<>();
        reservations.put(reservation.getId(), reservation);

        when(bookstore.getReservations()).thenReturn(reservations);

        LocalDate startDate = LocalDate.of(2021, 12, 1);
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        List<ReservationReport> report = reportActions.generateReservationReport(startDate, endDate);

        assertEquals(1, report.size(), "Expected to find one reservation in the report.");
        ReservationReport foundReport = report.get(0);
        assertEquals(book.getTitle(), foundReport.getBook().getTitle(), "Book title should match.");
        assertEquals(customer.getFullName(), foundReport.getCustomer().getFullName(), "Customer name should match.");
        assertEquals(2, foundReport.getQuantityReserved(), "Quantity reserved should match.");
    }

    @Test
    void testGenerateSalesReport() {
        Book book = new Book(UUID.randomUUID(), "Sample Book", "Author Name", 2021, 29.99, 10, 0, true, new Category("Fiction"));
        Customer customer = new Customer("Jane", "Doe", "Metropolis", "123 Elm Street", "12345", "555-1234", "jane.doe@example.com");

        Sale sale1 = new Sale(book, customer, 3, 89.97);
        sale1.setSaleDate(LocalDateTime.of(2021, 12, 10, 10, 0));

        Map<UUID, Sale> sales = new HashMap<>();
        sales.put(sale1.getId(), sale1);

        when(bookstore.getSales()).thenReturn(sales);

        LocalDate startDate = LocalDate.of(2021, 12, 1);
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        List<SalesReport> reports = reportActions.generateSalesReport(startDate, endDate);

        assertEquals(1, reports.size(), "Expected to find one sale in the report.");
        SalesReport report = reports.get(0);
        assertEquals(book.getTitle(), report.getBook().getTitle(), "Book title should match.");
        assertEquals(customer.getFullName(), report.getCustomer().getFullName(), "Customer name should match.");
    }
}
