package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.batch.BatchRequest;
import com.stringstack.talentos.dto.batch.BatchResponse;
import com.stringstack.talentos.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponse> createBatch(
            @Valid @RequestBody BatchRequest request) {

        return new ResponseEntity<>(
                batchService.createBatch(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BatchResponse>> getAllBatches() {

        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchResponse> getBatchById(
            @PathVariable Long id) {

        return ResponseEntity.ok(batchService.getBatchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatchResponse> updateBatch(
            @PathVariable Long id,
            @Valid @RequestBody BatchRequest request) {

        return ResponseEntity.ok(
                batchService.updateBatch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBatch(
            @PathVariable Long id) {

        batchService.deleteBatch(id);

        return ResponseEntity.ok("Batch deleted successfully.");
    }

}