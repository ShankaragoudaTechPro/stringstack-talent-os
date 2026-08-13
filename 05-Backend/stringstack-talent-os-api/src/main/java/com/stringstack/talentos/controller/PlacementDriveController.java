package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.placementDrive.PlacementDriveRequest;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.PlacementDriveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/placement-drives")
@RequiredArgsConstructor
public class PlacementDriveController {

    private final PlacementDriveService placementDriveService;

    // Create Placement Drive
    @PostMapping
    public ResponseEntity<ApiResponse<PlacementDriveResponse>> createPlacementDrive(
            @Valid @RequestBody PlacementDriveRequest request) {

        PlacementDriveResponse response = placementDriveService.createPlacementDrive(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<PlacementDriveResponse>builder()
                                .success(true)
                                .message("Placement Drive created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    // Get All Placement Drives
    @GetMapping
    public ResponseEntity<ApiResponse<List<PlacementDriveResponse>>> getAllPlacementDrives() {

        List<PlacementDriveResponse> response = placementDriveService.getAllPlacementDrives();

        return ResponseEntity.ok(
                ApiResponse.<List<PlacementDriveResponse>>builder()
                        .success(true)
                        .message("Placement Drives fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get Placement Drive By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementDriveResponse>> getPlacementDriveById(
            @PathVariable Long id) {

        PlacementDriveResponse response = placementDriveService.getPlacementDriveById(id);

        return ResponseEntity.ok(
                ApiResponse.<PlacementDriveResponse>builder()
                        .success(true)
                        .message("Placement Drive fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Update Placement Drive
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementDriveResponse>> updatePlacementDrive(
            @PathVariable Long id,
            @Valid @RequestBody PlacementDriveRequest request) {

        PlacementDriveResponse response = placementDriveService.updatePlacementDrive(id, request);

        return ResponseEntity.ok(
                ApiResponse.<PlacementDriveResponse>builder()
                        .success(true)
                        .message("Placement Drive updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Delete Placement Drive
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deletePlacementDrive(
            @PathVariable Long id) {

        placementDriveService.deletePlacementDrive(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Placement Drive deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}