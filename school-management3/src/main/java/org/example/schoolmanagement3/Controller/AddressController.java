package org.example.schoolmanagement3.Controller;

import jakarta.validation.Valid;
import org.example.schoolmanagement3.Api.ApiException;
import org.example.schoolmanagement3.DTO.AddressDTO;
import org.example.schoolmanagement3.Service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<?> addTeacherAddress(@PathVariable Integer teacherId, @RequestBody @Valid AddressDTO addressDTO)
            throws ApiException {

        addressService.addTeacherAddress(teacherId, addressDTO);

        return ResponseEntity.status(201).body("Address added successfully");
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<?> updateTeacherAddress(@PathVariable Integer teacherId, @RequestBody @Valid AddressDTO addressDTO)
            throws ApiException {

        addressService.updateTeacherAddress(teacherId, addressDTO);

        return ResponseEntity.status(200).body("Address updated successfully");
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<?> deleteTeacherAddress(@PathVariable Integer teacherId)
            throws ApiException {

        addressService.deleteTeacherAddress(teacherId);

        return ResponseEntity.status(200).body("Address deleted successfully");
    }
}
