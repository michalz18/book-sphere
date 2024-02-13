package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private UUID id;
    private String title;
    private String author;
    private int publicationDate;
    private double price;
    private int numberOfCopiesAvailable;
    private int numberOfReservations;
    private boolean isAvailable;
    private Category category;

    public Book(String title, String author, int publicationDate, double price, int numberOfCopiesAvailable, Category category) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.price = price;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
        this.numberOfReservations = 0;
        this.isAvailable = numberOfCopiesAvailable > 0;
        this.category = category;
    }
    public boolean reserveCopies(int quantity) {
        if (quantity <= (numberOfCopiesAvailable - numberOfReservations)) {
            numberOfReservations += quantity;
            updateAvailability();
            return true;
        }
        return false;
    }

    private void updateAvailability() {
        this.isAvailable = (numberOfCopiesAvailable - numberOfReservations) > 0;
    }
}
