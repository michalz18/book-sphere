package com.example.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String postCode;
    private String phoneNumber;
    private String email;

    public Customer(String firstName, String lastName, String city, String street, String postCode, String phoneNumber, String email) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
