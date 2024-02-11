package com.example.model;

public class OperationResult {
    private final boolean success;
    private final String message;

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OperationResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
