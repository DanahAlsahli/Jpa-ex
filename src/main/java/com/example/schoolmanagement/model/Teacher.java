package com.example.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "Age must not be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must not exceed 100")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Salary must not be null")
    @Positive(message = "Salary must be positive")
    @Column(nullable = false)
    private Double salary;

    // One-to-One relationship with Address
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}