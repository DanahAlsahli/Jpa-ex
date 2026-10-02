package org.example.schoolmanagement3.Service;

import org.example.schoolmanagement3.Api.ApiException;
import org.example.schoolmanagement3.DTO.CourseDTO;
import org.example.schoolmanagement3.Model.Course;
import org.example.schoolmanagement3.Model.Student;
import org.example.schoolmanagement3.Model.Teacher;
import org.example.schoolmanagement3.Repository.CourseRepository;
import org.example.schoolmanagement3.Repository.StudentRepository;
import org.example.schoolmanagement3.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(CourseDTO courseDTO) {

        Course course = new Course();

        course.setName(courseDTO.getName());

        return courseRepository.save(course);
    }

    public void updateCourse(Integer id, CourseDTO courseDTO)
            throws ApiException {

        Course course = courseRepository.findById(id).orElseThrow(() -> new ApiException("Course not found"));
        course.setName(courseDTO.getName());

        courseRepository.save(course);
    }

    public void deleteCourse(Integer id)
            throws ApiException {

        Course course = courseRepository.findById(id).orElseThrow(() -> new ApiException("Course not found"));

        courseRepository.delete(course);
    }

    public void assignTeacherToCourse(Integer teacherId, Integer courseId)
            throws ApiException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ApiException("Teacher not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ApiException("Course not found"));
        course.setTeacher(teacher);

        courseRepository.save(course);
    }

    public String getTeacherNameByCourseId(Integer courseId)
            throws ApiException {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ApiException("Course not found"));

        if (course.getTeacher() == null) {
            throw new ApiException("No teacher assigned to this course");
        }

        return course.getTeacher().getName();
    }

    public void assignStudentToCourse(Integer studentId, Integer courseId)
            throws ApiException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApiException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ApiException("Course not found"));

        course.getStudents().add(student);

        courseRepository.save(course);
    }
}