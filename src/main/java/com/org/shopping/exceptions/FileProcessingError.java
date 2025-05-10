package com.org.shopping.exceptions;

public class FileProcessingError extends RuntimeException {
    public FileProcessingError(String message) {
        super(message);
    }
}
