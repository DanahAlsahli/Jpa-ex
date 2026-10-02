package org.example.schoolmanagement2.Service;

import org.example.schoolmanagement2.Api.ApiException;
import org.example.schoolmanagement2.DTO.AddressDTO;
import org.example.schoolmanagement2.Model.Address;
import org.example.schoolmanagement2.Model.Teacher;
import org.example.schoolmanagement2.Repository.AddressRepository;
import org.example.schoolmanagement2.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public AddressService(AddressRepository addressRepository,
                          TeacherRepository teacherRepository) {
        this.addressRepository = addressRepository;
        this.teacherRepository = teacherRepository;
    }

    public void addTeacherAddress(Integer teacherId, AddressDTO addressDTO)
            throws ApiException {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ApiException("Teacher not found"));

        Address address = new Address();

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setTeacher(teacher);

        addressRepository.save(address);
    }

    public void updateTeacherAddress(Integer teacherId, AddressDTO addressDTO)
            throws ApiException {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ApiException("Teacher not found"));

        if (teacher.getAddress() == null) {
            throw new ApiException("Address not found");
        }

        Address address = addressRepository.findById(
                teacher.getAddress().getId()
        ).orElseThrow(() -> new ApiException("Address not found"));

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(address);
    }

    public void deleteTeacherAddress(Integer teacherId)
            throws ApiException {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ApiException("Teacher not found"));

        if (teacher.getAddress() == null) {
            throw new ApiException("Address not found");
        }

        Address address = teacher.getAddress();

        addressRepository.delete(address);
    }
}
