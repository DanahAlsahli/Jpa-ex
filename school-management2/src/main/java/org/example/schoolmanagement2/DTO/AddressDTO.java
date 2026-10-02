package org.example.schoolmanagement2.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Integer id;

    @NotEmpty(message = "Area is required")
    private String area;

    @NotEmpty(message = "Street is required")
    private String street;

    @NotEmpty(message = "Building number is required")
    private String buildingNumber;
}
