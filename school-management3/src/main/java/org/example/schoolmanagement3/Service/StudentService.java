package org.example.schoolmanagement3.Service;

import org.example.schoolmanagement3.Api.ApiException;
import org.example.schoolmanagement3.DTO.StudentDTO;
import org.example.schoolmanagement3.Model.Course;
import org.example.schoolmanagement3.Model.Student;
import org.example.schoolmanagement3.Repository.CourseRepository;
import org.example.schoolmanagement3.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDTO studentDTO) {

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setMajor(studentDTO.getMajor());

        return studentRepository.save(student);
    }

    public void updateStudent(Integer id, StudentDTO studentDTO)
            throws ApiException {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ApiException("Student not found"));

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setMajor(studentDTO.getMajor());

        studentRepository.save(student);
    }

    public void deleteStudent(Integer id)
            throws ApiException {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ApiException("Student not found"));

        studentRepository.delete(student);
    }

    public void updateMajor(Integer id, String major)
            throws ApiException {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ApiException("Student not found"));

        List<Course> courses = student.getCourses();

        if (courses != null) {

            for (Course course : new ArrayList<>(courses)) {
                course.getStudents().remove(student);
                courseRepository.save(course);
            }
        }

        student.setMajor(major);

        studentRepository.save(student);
    }

    public List<Course> getStudentCourses(Integer id)
            throws ApiException {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ApiException("Student not found"));

        return student.getCourses();
    }
}
