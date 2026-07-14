package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.course.CourseRequest;
import com.stringstack.talentos.dto.course.CourseResponse;
import com.stringstack.talentos.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response = courseService.createCourse(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        return ResponseEntity.ok(
                courseService.updateCourse(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully.");
    }
}