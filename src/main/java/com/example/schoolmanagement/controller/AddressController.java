package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.Api.ApiResponse;
import com.example.schoolmanagement.DTO.AddressDTO;
import com.example.schoolmanagement.model.Address;
import com.example.schoolmanagement.service.AddressService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Address> addTeacherAddress(@PathVariable Integer teacherId, @Valid @RequestBody AddressDTO addressDTO)
            throws ApiException {

        return ResponseEntity.ok(addressService.addTeacherAddress(teacherId, addressDTO)
        );
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<Address> updateTeacherAddress(@PathVariable Integer teacherId, @Valid @RequestBody AddressDTO addressDTO)
            throws ApiException {

        return ResponseEntity.ok(addressService.updateTeacherAddress(teacherId, addressDTO)
        );
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<ApiResponse> deleteTeacherAddress(@PathVariable Integer teacherId)
            throws ApiException {

        addressService.deleteTeacherAddress(teacherId);

        return ResponseEntity.ok(new ApiResponse("Teacher address deleted successfully")
        );
    }
}
