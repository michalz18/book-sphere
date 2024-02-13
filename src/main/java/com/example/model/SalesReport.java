package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesReport {
    private Book book;
    private Customer customer;
    private LocalDateTime saleDate;
    private int quantitySold;
    private double totalSaleAmount;
}
