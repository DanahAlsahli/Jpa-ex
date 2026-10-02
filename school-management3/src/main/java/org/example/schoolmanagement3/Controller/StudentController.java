package org.example.schoolmanagement3.Controller;

import jakarta.validation.Valid;
import org.example.schoolmanagement3.Api.ApiException;
import org.example.schoolmanagement3.DTO.StudentDTO;
import org.example.schoolmanagement3.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {

        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid StudentDTO studentDTO) {

        return ResponseEntity.status(201).body(studentService.addStudent(studentDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentDTO studentDTO)
            throws ApiException {

        studentService.updateStudent(id, studentDTO);

        return ResponseEntity.status(200).body("Student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id)
            throws ApiException {

        studentService.deleteStudent(id);

        return ResponseEntity.status(200).body("Student deleted successfully");
    }

    @PutMapping("/major/{id}")
    public ResponseEntity<?> updateMajor(@PathVariable Integer id, @RequestParam String major)
            throws ApiException {

        studentService.updateMajor(id, major);

        return ResponseEntity.status(200).body("Student major updated successfully");
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getStudentCourses(@PathVariable Integer id)
            throws ApiException {

        return ResponseEntity.status(200).body(studentService.getStudentCourses(id));
    }
}
