package com.example.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonIgnore
    private Teacher teacher;
}