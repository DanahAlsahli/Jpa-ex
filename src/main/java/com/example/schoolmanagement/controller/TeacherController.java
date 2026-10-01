package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Address;
import com.example.schoolmanagement.model.Teacher;
import com.example.schoolmanagement.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "http://localhost:5173") // للسماح لـ React بالاتصال
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable Integer id,
            @Valid @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<Teacher> addTeacherAddress(
            @PathVariable Integer id,
            @Valid @RequestBody Address address) {
        return new ResponseEntity<>(teacherService.addTeacherAddress(id, address), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Teacher> updateTeacherAddress(
            @PathVariable Integer id,
            @Valid @RequestBody Address address) {
        return ResponseEntity.ok(teacherService.updateTeacherAddress(id, address));
    }

    @DeleteMapping("/{id}/address")
    public ResponseEntity<Teacher> deleteTeacherAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.deleteTeacherAddress(id));
    }
}