package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.placement.PlacementRequest;
import com.stringstack.talentos.dto.placement.PlacementResponse;
import com.stringstack.talentos.service.PlacementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/placements")
@RequiredArgsConstructor
public class PlacementController {

    private final PlacementService placementService;

    @PostMapping
    public ResponseEntity<PlacementResponse> createPlacement(
            @Valid @RequestBody PlacementRequest request) {

        return new ResponseEntity<>(
                placementService.createPlacement(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlacementResponse>> getAllPlacements() {

        return ResponseEntity.ok(
                placementService.getAllPlacements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacementResponse> getPlacementById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                placementService.getPlacementById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacementResponse> updatePlacement(
            @PathVariable Long id,
            @Valid @RequestBody PlacementRequest request) {

        return ResponseEntity.ok(
                placementService.updatePlacement(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlacement(
            @PathVariable Long id) {

        placementService.deletePlacement(id);

        return ResponseEntity.ok("Placement deleted successfully.");
    }
}