package com.example.schoolmanagement.service;

import com.example.schoolmanagement.exception.ResourceNotFoundException;
import com.example.schoolmanagement.model.Address;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.repository.AddressRepository;
import com.example.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, AddressRepository addressRepository) {
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
    }

    // 1. Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // 2. Add new teacher
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // 3. Update teacher (personal info only)
    public Teacher updateTeacher(Integer id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));

        teacher.setName(teacherDetails.getName());
        teacher.setAge(teacherDetails.getAge());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setSalary(teacherDetails.getSalary());

        return teacherRepository.save(teacher);
    }

    // 4. Delete teacher
    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }

    // 5. Add teacher address
    public Teacher addTeacherAddress(Integer teacherId, Address address) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

        if (teacher.getAddress() != null) {
            throw new RuntimeException("Teacher already has an address. Use update instead.");
        }

        teacher.setAddress(address);
        return teacherRepository.save(teacher);
    }

    // 6. Update teacher address
    public Teacher updateTeacherAddress(Integer teacherId, Address addressDetails) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

        Address address = teacher.getAddress();
        if (address == null) {
            throw new ResourceNotFoundException("Teacher does not have an address to update");
        }

        address.setArea(addressDetails.getArea());
        address.setStreet(addressDetails.getStreet());
        address.setBuildingNumber(addressDetails.getBuildingNumber());

        return teacherRepository.save(teacher);
    }

    // 7. Delete teacher address
    public Teacher deleteTeacherAddress(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

        if (teacher.getAddress() == null) {
            throw new ResourceNotFoundException("Teacher does not have an address to delete");
        }

        teacher.setAddress(null);
        return teacherRepository.save(teacher);
    }

    // 8. Get teacher details by id
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }
}