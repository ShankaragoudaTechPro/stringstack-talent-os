package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.resume.ResumeRequest;
import com.stringstack.talentos.dto.resume.ResumeResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // Create Resume
    @PostMapping
    public ResponseEntity<ApiResponse<ResumeResponse>> createResume(
            @Valid @RequestBody ResumeRequest request) {

        ResumeResponse response = resumeService.createResume(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<ResumeResponse>builder()
                                .success(true)
                                .message("Resume created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    // Get All Resumes
    @GetMapping
    public ResponseEntity<ApiResponse<List<ResumeResponse>>> getAllResumes() {

        List<ResumeResponse> response = resumeService.getAllResumes();

        return ResponseEntity.ok(
                ApiResponse.<List<ResumeResponse>>builder()
                        .success(true)
                        .message("Resumes fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get Resume By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResumeResponse>> getResumeById(
            @PathVariable Long id) {

        ResumeResponse response = resumeService.getResumeById(id);

        return ResponseEntity.ok(
                ApiResponse.<ResumeResponse>builder()
                        .success(true)
                        .message("Resume fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Update Resume
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResumeResponse>> updateResume(
            @PathVariable Long id,
            @Valid @RequestBody ResumeRequest request) {

        ResumeResponse response = resumeService.updateResume(id, request);

        return ResponseEntity.ok(
                ApiResponse.<ResumeResponse>builder()
                        .success(true)
                        .message("Resume updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Delete Resume
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteResume(
            @PathVariable Long id) {

        resumeService.deleteResume(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Resume deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}