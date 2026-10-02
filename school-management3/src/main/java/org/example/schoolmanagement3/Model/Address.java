package org.example.schoolmanagement3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Area is required")
    private String area;

    @NotEmpty(message = "Street is required")
    private String street;

    @NotEmpty(message = "Building number is required")
    private String buildingNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}