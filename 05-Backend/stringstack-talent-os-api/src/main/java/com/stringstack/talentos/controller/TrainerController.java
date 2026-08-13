package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.trainer.TrainerRequest;
import com.stringstack.talentos.dto.trainer.TrainerResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrainerResponse>> createTrainer(
            @Valid @RequestBody TrainerRequest request) {

        TrainerResponse response = trainerService.createTrainer(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<TrainerResponse>builder()
                                .success(true)
                                .message("Trainer created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TrainerResponse>>> getAllTrainers() {

        List<TrainerResponse> response = trainerService.getAllTrainers();

        return ResponseEntity.ok(
                ApiResponse.<List<TrainerResponse>>builder()
                        .success(true)
                        .message("Trainers fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainerResponse>> getTrainerById(
            @PathVariable Long id) {

        TrainerResponse response = trainerService.getTrainerById(id);

        return ResponseEntity.ok(
                ApiResponse.<TrainerResponse>builder()
                        .success(true)
                        .message("Trainer fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainerResponse>> updateTrainer(
            @PathVariable Long id,
            @Valid @RequestBody TrainerRequest request) {

        TrainerResponse response = trainerService.updateTrainer(id, request);

        return ResponseEntity.ok(
                ApiResponse.<TrainerResponse>builder()
                        .success(true)
                        .message("Trainer updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteTrainer(
            @PathVariable Long id) {

        trainerService.deleteTrainer(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Trainer deleted successfully")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}