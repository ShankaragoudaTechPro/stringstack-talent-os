package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.student.StudentRequest;
import com.stringstack.talentos.dto.student.StudentResponse;
import com.stringstack.talentos.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Create Student
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        StudentResponse response = studentService.createStudent(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Get Student By Id
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully.");
    }
}