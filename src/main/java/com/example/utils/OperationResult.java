package com.example.utils;

import lombok.Getter;

@Getter
public record OperationResult(boolean success, String message) {

    @Override
    public String toString() {
        return "OperationResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
