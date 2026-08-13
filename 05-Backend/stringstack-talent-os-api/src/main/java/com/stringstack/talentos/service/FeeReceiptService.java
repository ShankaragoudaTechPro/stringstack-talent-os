package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.FeeReceiptResponse;

public interface FeeReceiptService {

    FeeReceiptResponse getReceiptDetails(Long paymentId);

    byte[] generateReceiptPdf(Long paymentId);
}