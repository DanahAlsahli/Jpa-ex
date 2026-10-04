package com.example.schoolmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Area is required")
    private String area;

    @NotBlank(message = "Street is required")
    private String street;

    @NotNull(message = "Building number is required")
    @Positive(message = "Building number must be greater than zero")
    private Integer buildingNumber;
}
