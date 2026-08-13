package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.CreateInstallmentRequest;
import com.stringstack.talentos.dto.fees.FeeInstallmentResponse;

import java.util.List;

public interface FeeInstallmentService {

    List<FeeInstallmentResponse> generateInstallments(
            CreateInstallmentRequest request
    );

    List<FeeInstallmentResponse> getInstallmentsByStudentFee(
            Long studentFeeId
    );

    FeeInstallmentResponse getInstallmentById(
            Long id
    );
}