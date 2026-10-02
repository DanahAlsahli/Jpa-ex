package org.example.schoolmanagement2.Service;

import org.example.schoolmanagement2.Api.ApiException;
import org.example.schoolmanagement2.DTO.TeacherDTO;
import org.example.schoolmanagement2.Model.Teacher;
import org.example.schoolmanagement2.Repository.TeacherRepository;
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

    public void updateTeacher(Integer id, TeacherDTO teacherDTO)
            throws ApiException {

        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));

        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSalary(teacherDTO.getSalary());

        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) throws ApiException {

        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));

        teacherRepository.delete(teacher);
    }

    public Teacher getTeacherById(Integer id)
            throws ApiException {

        return teacherRepository.findById(id).orElseThrow(() -> new ApiException("Teacher not found"));
    }
}
