package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.PaymentPlanRequest;
import com.stringstack.talentos.dto.fees.PaymentPlanResponse;
import com.stringstack.talentos.service.PaymentPlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-plans")
@RequiredArgsConstructor
public class PaymentPlanController {

    private final PaymentPlanService paymentPlanService;

    @PostMapping
    public ResponseEntity<PaymentPlanResponse> createPaymentPlan(
            @Valid @RequestBody PaymentPlanRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentPlanService.createPaymentPlan(request));
    }

    @GetMapping
    public ResponseEntity<List<PaymentPlanResponse>> getAllPaymentPlans() {

        return ResponseEntity.ok(
                paymentPlanService.getAllPaymentPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentPlanResponse> getPaymentPlanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentPlanService.getPaymentPlanById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentPlanResponse> updatePaymentPlan(
            @PathVariable Long id,
            @Valid @RequestBody PaymentPlanRequest request) {

        return ResponseEntity.ok(
                paymentPlanService.updatePaymentPlan(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePaymentPlan(
            @PathVariable Long id) {

        paymentPlanService.deactivatePaymentPlan(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activatePaymentPlan(
            @PathVariable Long id) {

        paymentPlanService.activatePaymentPlan(id);

        return ResponseEntity.noContent().build();
    }
}