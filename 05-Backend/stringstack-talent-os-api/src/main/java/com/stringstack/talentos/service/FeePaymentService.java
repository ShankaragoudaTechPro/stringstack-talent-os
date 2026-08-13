package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.CreatePaymentRequest;
import com.stringstack.talentos.dto.fees.FeePaymentResponse;

import java.util.List;

public interface FeePaymentService {

    FeePaymentResponse createPayment(
            CreatePaymentRequest request);

    FeePaymentResponse getPaymentById(Long id);

    List<FeePaymentResponse> getPaymentsByInstallment(
            Long installmentId);

    List<FeePaymentResponse> getPaymentsByStudentFee(
            Long studentFeeId);
}