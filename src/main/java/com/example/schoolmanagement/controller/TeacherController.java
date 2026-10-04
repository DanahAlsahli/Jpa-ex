package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.Api.ApiResponse;
import com.example.schoolmanagement.DTO.TeacherDTO;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Teacher>> getAllTeachers() {

        return ResponseEntity.ok(
                teacherService.getAllTeachers()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {

        return ResponseEntity.ok(teacherService.addTeacher(teacherDTO)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Integer id, @Valid @RequestBody TeacherDTO teacherDTO)
            throws ApiException {

        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer id)
            throws ApiException {

        teacherService.deleteTeacher(id);

        return ResponseEntity.ok(new ApiResponse("Teacher deleted successfully")
        );
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Teacher> getTeacherDetails(@PathVariable Integer id)
            throws ApiException {

        return ResponseEntity.ok(teacherService.getTeacherById(id)
        );
    }
}