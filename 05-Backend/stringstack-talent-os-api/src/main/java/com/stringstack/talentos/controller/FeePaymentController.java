package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.CreatePaymentRequest;
import com.stringstack.talentos.dto.fees.FeePaymentResponse;
import com.stringstack.talentos.service.FeePaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fee-payments")
@RequiredArgsConstructor
public class FeePaymentController {

    private final FeePaymentService feePaymentService;

    @PostMapping
    public ResponseEntity<FeePaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        feePaymentService.createPayment(request)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeePaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feePaymentService.getPaymentById(id)
        );
    }

    @GetMapping("/installment/{installmentId}")
    public ResponseEntity<List<FeePaymentResponse>>
    getPaymentsByInstallment(
            @PathVariable Long installmentId) {

        return ResponseEntity.ok(
                feePaymentService
                        .getPaymentsByInstallment(installmentId)
        );
    }

    @GetMapping("/student-fee/{studentFeeId}")
    public ResponseEntity<List<FeePaymentResponse>>
    getPaymentsByStudentFee(
            @PathVariable Long studentFeeId) {

        return ResponseEntity.ok(
                feePaymentService
                        .getPaymentsByStudentFee(studentFeeId)
        );
    }
}