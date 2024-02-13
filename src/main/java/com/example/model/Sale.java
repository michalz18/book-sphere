package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private UUID id;
    private Book book;
    private Customer customer;
    private int quantitySold;
    private LocalDateTime saleDate;
    private double totalPrice;

    public Sale(Book book, Customer customer, int quantitySold, double totalPrice) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.customer = customer;
        this.quantitySold = quantitySold;
        this.saleDate = LocalDateTime.now();
        this.totalPrice = totalPrice;
    }
}
