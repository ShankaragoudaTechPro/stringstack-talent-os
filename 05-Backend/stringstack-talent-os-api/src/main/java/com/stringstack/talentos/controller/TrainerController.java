package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.trainer.TrainerRequest;
import com.stringstack.talentos.dto.trainer.TrainerResponse;
import com.stringstack.talentos.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerResponse> createTrainer(
            @Valid @RequestBody TrainerRequest request) {

        return new ResponseEntity<>(
                trainerService.createTrainer(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAllTrainers() {

        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponse> getTrainerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponse> updateTrainer(
            @PathVariable Long id,
            @Valid @RequestBody TrainerRequest request) {

        return ResponseEntity.ok(
                trainerService.updateTrainer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(
            @PathVariable Long id) {

        trainerService.deleteTrainer(id);

        return ResponseEntity.ok("Trainer deleted successfully.");
    }

}