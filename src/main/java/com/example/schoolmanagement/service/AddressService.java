package com.example.schoolmanagement.service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.DTO.AddressDTO;
import com.example.schoolmanagement.model.Address;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.repository.AddressRepository;
import com.example.schoolmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public AddressService(
            AddressRepository addressRepository,
            TeacherRepository teacherRepository) {

        this.addressRepository = addressRepository;
        this.teacherRepository = teacherRepository;
    }

    public Address addTeacherAddress(Integer teacherId, AddressDTO addressDTO) throws ApiException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ApiException("Teacher not found"));

        if (addressRepository.existsById(teacherId)) {
            throw new ApiException("Teacher already has an address");
        }

        Address address = new Address();

        address.setId(teacher.getId());
        address.setTeacher(teacher);
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(
                addressDTO.getBuildingNumber()
        );

        return addressRepository.save(address);
    }

    public Address updateTeacherAddress(Integer teacherId, AddressDTO addressDTO) throws ApiException {

        Address address = addressRepository.findById(teacherId).orElseThrow(() -> new ApiException("Teacher address not found"));

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(
                addressDTO.getBuildingNumber()
        );

        return addressRepository.save(address);
    }

    public void deleteTeacherAddress(
            Integer teacherId) throws ApiException {

        Address address = addressRepository.findById(teacherId).orElseThrow(() -> new ApiException("Teacher address not found"));

        addressRepository.delete(address);
    }
}
