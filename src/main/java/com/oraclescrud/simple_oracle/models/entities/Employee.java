package com.oraclescrud.simple_oracle.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// @Data - Anotasi Lombok untuk menghasilkan getter, setter, equals, hashCode, dan toString methods
@Data
// @NoArgsConstructor - Anotasi Lombok untuk membuat constructor tanpa parameter
@NoArgsConstructor  
// @AllArgsConstructor - Anotasi Lombok untuk membuat constructor dengan semua parameter
@AllArgsConstructor
// @Entity - Menandakan bahwa class ini adalah entitas JPA yang akan dipetakan ke tabel database
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
