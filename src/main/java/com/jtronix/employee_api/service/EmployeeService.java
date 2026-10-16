package com.jtronix.employee_api.service;

import com.jtronix.employee_api.dto.CreateEmployeeRequest;
import com.jtronix.employee_api.exception.EmployeeNotFoundException;
import com.jtronix.employee_api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<Long, Employee> employees = new ConcurrentHashMap<>();

    public EmployeeService() {
        employees.put(1L, new Employee(1L, "Rahul Sharma", "Engineering", "Active"));
        employees.put(2L, new Employee(2L, "Priya Patel", "HR", "Active"));
        employees.put(3L, new Employee(3L, "Amit Verma", "Engineering", "Inactive"));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employees.get(id);

        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }

        return employee;
    }

    private final AtomicLong idCounter = new AtomicLong(3);

    public Employee createEmployee(CreateEmployeeRequest request) {
        Long id = idCounter.incrementAndGet();

        Employee employee = new Employee(
                id,
                request.getName().trim(),
                request.getDepartment().trim(),
                "Active"
        );

        employees.put(id, employee);

        return employee;
    }

    public Employee updateEmployeeStatus(Long id, String status) {
        Employee employee = getEmployeeById(id);

        if (!status.equals("Active") && !status.equals("Inactive")) {
            throw new IllegalArgumentException(
                    "Status must be Active or Inactive"
            );
        }

        employee.setStatus(status);
        return employee;
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employees.values().stream()
                .filter(employee ->
                        employee.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
}
