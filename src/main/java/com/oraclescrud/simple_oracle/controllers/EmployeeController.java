package com.oraclescrud.simple_oracle.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oraclescrud.simple_oracle.models.entities.Employee;
import com.oraclescrud.simple_oracle.services.EmployeeService;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Get All Employees
    @PostMapping("/getall")
    public ResponseEntity<Page<Employee>> getAllEmployees(
        @RequestParam(required = false) String search,
        @PageableDefault(size = 10) Pageable pageable) {
        Page<Employee> employeesPage = employeeService.findAll(search, pageable);
        return ResponseEntity.ok(employeesPage);
    }

    // Get Employee By ID
    @PostMapping("/getbyid/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    // Update Employee
    @PostMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {
        Employee savedEmployee = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Delete Employee
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}