package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.fees.FeeReceiptResponse;
import com.stringstack.talentos.service.FeeReceiptService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fee-receipts")
@RequiredArgsConstructor
public class FeeReceiptController {

    private final FeeReceiptService feeReceiptService;

    // ==========================================
    // GET RECEIPT DETAILS
    // ==========================================

    @GetMapping("/{paymentId}")
    public ResponseEntity<FeeReceiptResponse> getReceiptDetails(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                feeReceiptService
                        .getReceiptDetails(paymentId)
        );
    }

    // ==========================================
    // GENERATE / DOWNLOAD PDF
    // ==========================================

    @GetMapping("/{paymentId}/pdf")
    public ResponseEntity<byte[]> downloadReceiptPdf(
            @PathVariable Long paymentId) {

        byte[] pdf =
                feeReceiptService
                        .generateReceiptPdf(paymentId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=fee-receipt-"
                                + paymentId
                                + ".pdf"
                )
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }
}