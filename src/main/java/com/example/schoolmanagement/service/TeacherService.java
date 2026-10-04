package com.example.schoolmanagement.service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.DTO.TeacherDTO;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }

    public Teacher addTeacher(TeacherDTO teacherDTO) {

        Teacher teacher = new Teacher();

        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSalary(teacherDTO.getSalary());

        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Integer id, TeacherDTO teacherDTO) throws ApiException {

        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));

        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSalary(teacherDTO.getSalary());

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) throws ApiException {

        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));

        teacherRepository.delete(teacher);
    }

    public Teacher getTeacherById(Integer id) throws ApiException {

        return teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));
    }
}