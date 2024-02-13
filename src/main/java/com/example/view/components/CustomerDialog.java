package com.example.view.components;

import com.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerDialog {
    public static Customer showDialog(Component parent) {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField streetField = new JTextField();
        JTextField postCodeField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField emailField = new JTextField();

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("Street:"));
        panel.add(streetField);
        panel.add(new JLabel("Post Code:"));
        panel.add(postCodeField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Add New Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return new Customer(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    cityField.getText(),
                    streetField.getText(),
                    postCodeField.getText(),
                    phoneNumberField.getText(),
                    emailField.getText()
            );
        }
        return null;
    }
}
