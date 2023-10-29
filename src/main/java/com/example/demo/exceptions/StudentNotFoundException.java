package com.example.demo.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
