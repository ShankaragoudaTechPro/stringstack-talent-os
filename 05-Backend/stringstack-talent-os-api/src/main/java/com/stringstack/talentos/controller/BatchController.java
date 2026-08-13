package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.batch.BatchRequest;
import com.stringstack.talentos.dto.batch.BatchResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    // Create Batch
    @PostMapping
    public ResponseEntity<ApiResponse<BatchResponse>> createBatch(
            @Valid @RequestBody BatchRequest request) {

        BatchResponse response = batchService.createBatch(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<BatchResponse>builder()
                                .success(true)
                                .message("Batch created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    // Get All Batches
    @GetMapping
    public ResponseEntity<ApiResponse<List<BatchResponse>>> getAllBatches() {

        List<BatchResponse> response = batchService.getAllBatches();

        return ResponseEntity.ok(
                ApiResponse.<List<BatchResponse>>builder()
                        .success(true)
                        .message("Batches fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Get Batch By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponse>> getBatchById(
            @PathVariable Long id) {

        BatchResponse response = batchService.getBatchById(id);

        return ResponseEntity.ok(
                ApiResponse.<BatchResponse>builder()
                        .success(true)
                        .message("Batch fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Update Batch
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BatchResponse>> updateBatch(
            @PathVariable Long id,
            @Valid @RequestBody BatchRequest request) {

        BatchResponse response = batchService.updateBatch(id, request);

        return ResponseEntity.ok(
                ApiResponse.<BatchResponse>builder()
                        .success(true)
                        .message("Batch updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // Delete Batch
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBatch(
            @PathVariable Long id) {

        batchService.deleteBatch(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Batch deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}