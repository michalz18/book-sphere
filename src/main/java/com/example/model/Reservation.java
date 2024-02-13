package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private UUID id;
    private Book book;
    private Customer customer;
    private int quantity;
    private LocalDateTime reservationDate;
    private boolean isActive;

    public Reservation(Book book, Customer customer, int quantity) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.customer = customer;
        this.quantity = quantity;
        this.reservationDate = LocalDateTime.now();
        this.isActive = true;
    }
}
