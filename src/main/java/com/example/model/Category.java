package com.example.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Category {
    private UUID id;
    private String name;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

}
