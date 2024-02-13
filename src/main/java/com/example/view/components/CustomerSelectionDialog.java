package com.example.view.components;

import com.example.model.Customer;
import com.example.model.Bookstore;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CustomerSelectionDialog {
    private final Bookstore bookstore;

    public CustomerSelectionDialog(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public Customer selectCustomer(Component parent) {
        JComboBox<Customer> comboBox = new JComboBox<>(new Vector<>(bookstore.getCustomers()));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Customer) {
                    setText(((Customer)value).getFirstName() + " " + ((Customer)value).getLastName());
                }
                return this;
            }
        });

        int result = JOptionPane.showConfirmDialog(parent, comboBox, "Select Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return (Customer) comboBox.getSelectedItem();
        }
        return null;
    }
}
