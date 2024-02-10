package com.example.model;

import java.util.UUID;

public class Category {
    private final UUID id;
    private final String name;

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
