package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.FeeStructureRequest;
import com.stringstack.talentos.dto.fees.FeeStructureResponse;
import com.stringstack.talentos.service.FeeStructureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-structures")
@RequiredArgsConstructor
public class FeeStructureController {

    private final FeeStructureService feeStructureService;

    @PostMapping
    public ResponseEntity<FeeStructureResponse> createFeeStructure(
            @Valid @RequestBody FeeStructureRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feeStructureService.createFeeStructure(request));
    }

    @GetMapping
    public ResponseEntity<List<FeeStructureResponse>> getAllFeeStructures() {

        return ResponseEntity.ok(
                feeStructureService.getAllFeeStructures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructureResponse> getFeeStructureById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeStructureService.getFeeStructureById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructureResponse> updateFeeStructure(
            @PathVariable Long id,
            @Valid @RequestBody FeeStructureRequest request) {

        return ResponseEntity.ok(
                feeStructureService.updateFeeStructure(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateFeeStructure(
            @PathVariable Long id) {

        feeStructureService.deactivateFeeStructure(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateFeeStructure(
            @PathVariable Long id) {

        feeStructureService.activateFeeStructure(id);

        return ResponseEntity.noContent().build();
    }
}