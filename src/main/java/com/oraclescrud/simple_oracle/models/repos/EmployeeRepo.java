package com.oraclescrud.simple_oracle.models.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.oraclescrud.simple_oracle.models.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    
    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrPositionContainingIgnoreCase(
        String name, String email, String phone, String position, Pageable pageable);
}
