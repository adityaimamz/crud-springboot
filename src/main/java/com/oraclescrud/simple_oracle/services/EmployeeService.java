package com.oraclescrud.simple_oracle.services;

import org.springframework.stereotype.Service;
import com.oraclescrud.simple_oracle.models.entities.Employee;
import com.oraclescrud.simple_oracle.models.repos.EmployeeRepo;
import com.oraclescrud.simple_oracle.exceptions.DuplicateEmailException;
import com.oraclescrud.simple_oracle.exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public Employee save(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            throw new DuplicateEmailException("Employee already exists with email: " + employee.getEmail());
        }
        if (employee.getEmail() == null || employee.getName() == null) {
            throw new IllegalArgumentException("Email dan nama tidak boleh kosong");
        }
        return employeeRepo.save(employee);
    }

    public Page<Employee> findAll(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return employeeRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrPositionContainingIgnoreCase(
                search, search, search, search, pageable);
        }
        return employeeRepo.findAll(pageable);
    }

    public Employee findById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteById(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepo.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        if (updatedEmployee.getEmail() == null || updatedEmployee.getName() == null) {
            throw new IllegalArgumentException("Email and name cannot be null");
        }
        
        Employee existingEmployee = findById(id);
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        return employeeRepo.save(existingEmployee);
    }
}
