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
    private boolean isAvailable;

    public Book(String title, String author, int publicationDate, double price, int numberOfCopiesAvailable) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.price = price;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
        this.isAvailable = numberOfCopiesAvailable > 0;
    }

    public void updateAvailableCopiesAfterTransaction(int copiesSold) {
        if (copiesSold <= this.numberOfCopiesAvailable) {
            this.numberOfCopiesAvailable -= copiesSold;
            this.isAvailable = this.numberOfCopiesAvailable > 0;
        } else {
            throw new IllegalArgumentException("Cannot sell more copies than are available.");
        }
    }
}
