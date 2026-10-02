package org.example.schoolmanagement2.Service;

import org.example.schoolmanagement2.Api.ApiException;
import org.example.schoolmanagement2.DTO.CourseDTO;
import org.example.schoolmanagement2.Model.Course;
import org.example.schoolmanagement2.Model.Teacher;
import org.example.schoolmanagement2.Repository.CourseRepository;
import org.example.schoolmanagement2.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
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
}