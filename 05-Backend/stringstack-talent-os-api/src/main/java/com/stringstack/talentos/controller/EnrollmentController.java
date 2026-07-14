package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.enrollment.EnrollmentRequest;
import com.stringstack.talentos.dto.enrollment.EnrollmentResponse;
import com.stringstack.talentos.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(
            @Valid @RequestBody EnrollmentRequest request) {

        return new ResponseEntity<>(
                enrollmentService.createEnrollment(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {

        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequest request) {

        return ResponseEntity.ok(
                enrollmentService.updateEnrollment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.ok("Enrollment deleted successfully.");
    }
}