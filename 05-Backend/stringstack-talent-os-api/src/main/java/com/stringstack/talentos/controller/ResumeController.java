package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.resume.ResumeRequest;
import com.stringstack.talentos.dto.resume.ResumeResponse;
import com.stringstack.talentos.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(
            @Valid @RequestBody ResumeRequest request) {

        return new ResponseEntity<>(
                resumeService.createResume(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResumeResponse>> getAllResumes() {

        return ResponseEntity.ok(
                resumeService.getAllResumes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resumeService.getResumeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumeResponse> updateResume(
            @PathVariable Long id,
            @Valid @RequestBody ResumeRequest request) {

        return ResponseEntity.ok(
                resumeService.updateResume(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(
            @PathVariable Long id) {

        resumeService.deleteResume(id);

        return ResponseEntity.ok("Resume deleted successfully.");
    }
}