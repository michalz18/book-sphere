package com.example.controller;

import com.example.model.Bookstore;
import com.example.model.Customer;
import com.example.utils.OperationResult;
import com.example.view.components.CustomerDialog;

import javax.swing.*;

public class CustomerActions {
    private final Bookstore bookstore;
    private final JFrame frame;

    public CustomerActions(Bookstore bookstore, JFrame frame) {
        this.bookstore = bookstore;
        this.frame = frame;
    }

    public void addCustomer() {
        CustomerDialog customerDialog = new CustomerDialog();
        Customer newCustomer = customerDialog.showDialog(frame);
        if (newCustomer != null) {
            OperationResult result = bookstore.addCustomer(newCustomer);
            JOptionPane.showMessageDialog(frame, result.message(), "Customer Addition", result.success() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        }
    }
}
