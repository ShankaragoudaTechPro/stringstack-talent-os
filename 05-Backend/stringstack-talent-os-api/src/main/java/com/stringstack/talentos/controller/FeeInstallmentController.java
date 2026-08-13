package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.CreateInstallmentRequest;
import com.stringstack.talentos.dto.fees.FeeInstallmentResponse;
import com.stringstack.talentos.service.FeeInstallmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-installments")
@RequiredArgsConstructor
public class FeeInstallmentController {

    private final FeeInstallmentService feeInstallmentService;

    @PostMapping("/generate")
    public ResponseEntity<List<FeeInstallmentResponse>>
    generateInstallments(
            @Valid
            @RequestBody
            CreateInstallmentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        feeInstallmentService
                                .generateInstallments(request)
                );
    }

    @GetMapping("/student-fee/{studentFeeId}")
    public ResponseEntity<List<FeeInstallmentResponse>>
    getInstallmentsByStudentFee(
            @PathVariable Long studentFeeId) {

        return ResponseEntity.ok(
                feeInstallmentService
                        .getInstallmentsByStudentFee(
                                studentFeeId
                        )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeInstallmentResponse>
    getInstallmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeInstallmentService
                        .getInstallmentById(id)
        );
    }
}