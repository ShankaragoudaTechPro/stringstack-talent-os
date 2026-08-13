package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.placement.PlacementRequest;
import com.stringstack.talentos.dto.placement.PlacementResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.PlacementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/placements")
@RequiredArgsConstructor
public class PlacementController {

    private final PlacementService placementService;

    // Create Placement
    @PostMapping
    public ResponseEntity<ApiResponse<PlacementResponse>> createPlacement(
            @Valid @RequestBody PlacementRequest request) {

        PlacementResponse response = placementService.createPlacement(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<PlacementResponse>builder()
                                .success(true)
                                .message("Placement created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    // Get All Placements
    @GetMapping
    public ResponseEntity<ApiResponse<List<PlacementResponse>>> getAllPlacements() {

        List<PlacementResponse> response = placementService.getAllPlacements();

        return ResponseEntity.ok(
                ApiResponse.<List<PlacementResponse>>builder()
                        .success(true)
                        .message("Placements fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get Placement By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementResponse>> getPlacementById(
            @PathVariable Long id) {

        PlacementResponse response = placementService.getPlacementById(id);

        return ResponseEntity.ok(
                ApiResponse.<PlacementResponse>builder()
                        .success(true)
                        .message("Placement fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Update Placement
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PlacementResponse>> updatePlacement(
            @PathVariable Long id,
            @Valid @RequestBody PlacementRequest request) {

        PlacementResponse response = placementService.updatePlacement(id, request);

        return ResponseEntity.ok(
                ApiResponse.<PlacementResponse>builder()
                        .success(true)
                        .message("Placement updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Delete Placement
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deletePlacement(
            @PathVariable Long id) {

        placementService.deletePlacement(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Placement deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}