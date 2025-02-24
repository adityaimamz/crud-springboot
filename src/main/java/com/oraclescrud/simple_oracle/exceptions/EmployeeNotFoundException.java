package com.oraclescrud.simple_oracle.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with ID " + id + " not found");
    }
} 