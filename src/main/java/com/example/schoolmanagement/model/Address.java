package com.example.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Area must not be empty")
    @Column(nullable = false)
    private String area;

    @NotEmpty(message = "Street must not be empty")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "Building number must not be null")
    @Positive(message = "Building number must be positive")
    @Column(nullable = false)
    private Integer buildingNumber;
}