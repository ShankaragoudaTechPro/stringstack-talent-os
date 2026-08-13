package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.attendance.AttendanceRequest;
import com.stringstack.talentos.dto.attendance.AttendanceResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Create Attendance
    @PostMapping
    public ResponseEntity<ApiResponse<AttendanceResponse>> createAttendance(
            @Valid @RequestBody AttendanceRequest request) {

        AttendanceResponse response = attendanceService.createAttendance(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<AttendanceResponse>builder()
                                .success(true)
                                .message("Attendance created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    // Get All Attendances
    @GetMapping
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendances() {

        List<AttendanceResponse> response = attendanceService.getAllAttendances();

        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceResponse>>builder()
                        .success(true)
                        .message("Attendances fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get Attendance By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AttendanceResponse>> getAttendanceById(
            @PathVariable Long id) {

        AttendanceResponse response = attendanceService.getAttendanceById(id);

        return ResponseEntity.ok(
                ApiResponse.<AttendanceResponse>builder()
                        .success(true)
                        .message("Attendance fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Update Attendance
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AttendanceResponse>> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRequest request) {

        AttendanceResponse response = attendanceService.updateAttendance(id, request);

        return ResponseEntity.ok(
                ApiResponse.<AttendanceResponse>builder()
                        .success(true)
                        .message("Attendance updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Attendance deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}