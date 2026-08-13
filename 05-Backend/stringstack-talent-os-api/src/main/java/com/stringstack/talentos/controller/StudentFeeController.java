package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.CreateStudentFeeRequest;
import com.stringstack.talentos.dto.fees.StudentFeeResponse;
import com.stringstack.talentos.service.StudentFeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-fees")
@RequiredArgsConstructor
public class StudentFeeController {

    private final StudentFeeService studentFeeService;

    @PostMapping
    public ResponseEntity<StudentFeeResponse> createStudentFee(
            @Valid @RequestBody CreateStudentFeeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentFeeService.createStudentFee(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentFeeResponse>>
    getAllStudentFees() {

        return ResponseEntity.ok(
                studentFeeService.getAllStudentFees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFeeResponse>
    getStudentFeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentFeeService.getStudentFeeById(id));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<StudentFeeResponse>
    getStudentFeeByEnrollmentId(
            @PathVariable Long enrollmentId) {

        return ResponseEntity.ok(
                studentFeeService
                        .getStudentFeeByEnrollmentId(enrollmentId));
    }
}