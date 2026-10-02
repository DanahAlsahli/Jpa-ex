package org.example.schoolmanagement2.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Salary is required")
    private Double salary;
}
