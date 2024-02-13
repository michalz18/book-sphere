package com.example.view.components;

import com.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerDialog {
    private final JTextField firstNameField = new JTextField();
    private final JTextField lastNameField = new JTextField();
    private final JTextField cityField = new JTextField();
    private final JTextField streetField = new JTextField();
    private final JTextField postCodeField = new JTextField();
    private final JTextField phoneNumberField = new JTextField();
    private final JTextField emailField = new JTextField();

    public CustomerDialog() {
    }

    public Customer showDialog(Component parent) {
        JPanel panel = createInputPanel();
        while (true) {
            int result = JOptionPane.showConfirmDialog(parent, panel, "Add New Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String errorMessage = validateCustomerInput();
                if (errorMessage.isEmpty()) {
                    return createCustomer();
                } else {
                    JOptionPane.showMessageDialog(parent, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                break;
            }
        }
        return null;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        addField(panel, "First Name:", firstNameField);
        addField(panel, "Last Name:", lastNameField);
        addField(panel, "City:", cityField);
        addField(panel, "Street:", streetField);
        addField(panel, "Post Code:", postCodeField);
        addField(panel, "Phone Number:", phoneNumberField);
        addField(panel, "Email:", emailField);
        return panel;
    }

    private void addField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private String validateCustomerInput() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String city = cityField.getText();
        String street = streetField.getText();
        String postCode = postCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();

        if (firstName == null || firstName.trim().isEmpty()) {
            return "First Name cannot be empty.";
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            return "Last Name cannot be empty.";
        }

        if (city == null || city.trim().isEmpty()) {
            return "City cannot be empty.";
        }

        if (street == null || street.trim().isEmpty()) {
            return "Street cannot be empty.";
        }

        if (postCode == null || postCode.trim().isEmpty()) {
            return "Post Code cannot be empty.";
        } else if (!postCode.matches("\\d{5}")) {
            return "Post Code must be 5 digits.";
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Phone Number cannot be empty.";
        } else if (!phoneNumber.matches("\\d{9}")) {
            return "Phone Number must be 9 digits.";
        }

        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Please enter a valid email address.";
        }

        return "";
    }


    private Customer createCustomer() {
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
}
