package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.placementDrive.PlacementDriveRequest;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveResponse;
import com.stringstack.talentos.service.PlacementDriveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/placement-drives")
@RequiredArgsConstructor
public class PlacementDriveController {

    private final PlacementDriveService placementDriveService;

    @PostMapping
    public ResponseEntity<PlacementDriveResponse> createPlacementDrive(
            @Valid @RequestBody PlacementDriveRequest request) {

        return new ResponseEntity<>(
                placementDriveService.createPlacementDrive(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlacementDriveResponse>> getAllPlacementDrives() {

        return ResponseEntity.ok(
                placementDriveService.getAllPlacementDrives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacementDriveResponse> getPlacementDriveById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                placementDriveService.getPlacementDriveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacementDriveResponse> updatePlacementDrive(
            @PathVariable Long id,
            @Valid @RequestBody PlacementDriveRequest request) {

        return ResponseEntity.ok(
                placementDriveService.updatePlacementDrive(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlacementDrive(
            @PathVariable Long id) {

        placementDriveService.deletePlacementDrive(id);

        return ResponseEntity.ok("Placement Drive deleted successfully.");
    }
}