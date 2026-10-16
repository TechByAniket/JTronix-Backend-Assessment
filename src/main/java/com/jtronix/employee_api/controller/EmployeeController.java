package com.jtronix.employee_api.controller;

import com.jtronix.employee_api.dto.CreateEmployeeRequest;
import com.jtronix.employee_api.dto.UpdateEmployeeStatusRequest;
import com.jtronix.employee_api.model.Employee;
import com.jtronix.employee_api.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(
            @RequestParam(required = false) String department) {

        if (department != null && !department.isBlank()) {
            return employeeService.getEmployeesByDepartment(department);
        }

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        Employee employee = employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employee);
    }

    @PatchMapping("/{id}/status")
    public Employee updateEmployeeStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeStatusRequest request) {

        return employeeService.updateEmployeeStatus(
                id, request.getStatus()
        );
    }
}
