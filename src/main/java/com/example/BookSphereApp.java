package com.example;

import com.example.controller.BookActions;
import com.example.controller.CustomerActions;
import com.example.controller.ReportActions;
import com.example.model.Bookstore;
import com.example.view.panels.ManageBooksPanel;
import com.example.view.panels.ReportPanel;

import javax.swing.*;
import java.awt.*;

public class BookSphereApp {

    private final Bookstore bookstore = new Bookstore();
    private JFrame frame;
    private JPanel cards;
    private final BookActions bookActions;
    private final CustomerActions customerActions;
    private final ReportActions reportActions;
    private final static String MANAGE_BOOKS_PANEL = "ManageBooksPanel";
    private static final String REPORT_PANEL = "ReportPanel";

    public BookSphereApp() {
        setupMainFrame();
        this.bookActions = new BookActions(bookstore, frame);
        this.customerActions = new CustomerActions(bookstore, frame);
        this.reportActions = new ReportActions(bookstore, frame);
        initializeUI();
    }

    private void initializeUI() {
        setupMainFrame();
        setupButtonPanel();
        setupCardsPanel();
        frame.setVisible(true);
    }

    private void setupMainFrame() {
        frame = new JFrame("BookSphere - Bookstore Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
    }

    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton manageBooksButton = new JButton("Manage Books");
        JButton addCategoryButton = new JButton("Add Category");
        JButton addCustomerButton = new JButton("Add Customer");
        JButton reportButton = new JButton("Reports");

        addCategoryButton.addActionListener(e -> bookActions.addCategory());
        addButton.addActionListener(e -> bookActions.addBook());
        manageBooksButton.addActionListener(e -> showManageBooksPanel());
        reportButton.addActionListener(e -> showReportPanel());
        addCustomerButton.addActionListener(e -> customerActions.addCustomer());

        buttonPanel.add(addButton);
        buttonPanel.add(manageBooksButton);
        buttonPanel.add(addCategoryButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(reportButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupCardsPanel() {
        cards = new JPanel(new CardLayout());

        ManageBooksPanel manageBooksPanel = new ManageBooksPanel(bookstore, bookActions);
        cards.add(manageBooksPanel, MANAGE_BOOKS_PANEL);

        ReportPanel reportPanel = new ReportPanel(reportActions, frame);
        cards.add(reportPanel, REPORT_PANEL);

        frame.add(cards, BorderLayout.CENTER);
    }


    private void showManageBooksPanel() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, MANAGE_BOOKS_PANEL);
    }
    private void showReportPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, REPORT_PANEL);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookSphereApp::new);
    }
}
