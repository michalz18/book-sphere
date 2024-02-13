package com.example.model.reports;

import com.example.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReport {
    private Book book;
    private int availableCopies;
}
