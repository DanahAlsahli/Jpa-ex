package org.example.schoolmanagement2.Controller;

import jakarta.validation.Valid;
import org.example.schoolmanagement2.Api.ApiException;
import org.example.schoolmanagement2.DTO.CourseDTO;
import org.example.schoolmanagement2.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {

        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid CourseDTO courseDTO) {

        return ResponseEntity.status(201).body(courseService.addCourse(courseDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody @Valid CourseDTO courseDTO)
            throws ApiException {

        courseService.updateCourse(id, courseDTO);

        return ResponseEntity.status(200).body("Course updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id)
            throws ApiException {

        courseService.deleteCourse(id);

        return ResponseEntity.status(200).body("Course deleted successfully");
    }

    @PostMapping("/assign/{teacherId}/{courseId}")
    public ResponseEntity<?> assignTeacherToCourse(@PathVariable Integer teacherId, @PathVariable Integer courseId)
            throws ApiException {

        courseService.assignTeacherToCourse(teacherId, courseId);

        return ResponseEntity.status(200).body("Teacher assigned to course successfully");
    }

    @GetMapping("/teacher/{courseId}")
    public ResponseEntity<?> getTeacherNameByCourseId(@PathVariable Integer courseId)
            throws ApiException {

        return ResponseEntity.status(200).body(courseService.getTeacherNameByCourseId(courseId));
    }
}