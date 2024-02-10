package com.example.model;

import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private String author;
    private int publicationDate;
    private double price;
    private int numberOfCopiesAvailable;

    public Book(String title, String author, int publicationDate, double price, int numberOfCopiesAvailable) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.price = price;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }
}
