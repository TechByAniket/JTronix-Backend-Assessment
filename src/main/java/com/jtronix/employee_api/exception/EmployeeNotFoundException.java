package com.jtronix.employee_api.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Employee not found with ID: " + id);
    }
}
