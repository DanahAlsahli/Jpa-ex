package org.example.schoolmanagement3.Controller;

import jakarta.validation.Valid;
import org.example.schoolmanagement3.Api.ApiException;
import org.example.schoolmanagement3.DTO.TeacherDTO;
import org.example.schoolmanagement3.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTeachers() {

        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {

        return ResponseEntity.status(201).body(teacherService.addTeacher(teacherDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody @Valid TeacherDTO teacherDTO)
            throws ApiException {

        teacherService.updateTeacher(id, teacherDTO);

        return ResponseEntity.status(200).body("Teacher updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id)
            throws ApiException {

        teacherService.deleteTeacher(id);

        return ResponseEntity.status(200).body("Teacher deleted successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer id)
            throws ApiException {

        return ResponseEntity.status(200).body(teacherService.getTeacherById(id));
    }
}
