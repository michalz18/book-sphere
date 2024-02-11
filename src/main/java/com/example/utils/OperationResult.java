package com.example.utils;

public record OperationResult(boolean success, String message) {

    @Override
    public String toString() {
        return "OperationResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
