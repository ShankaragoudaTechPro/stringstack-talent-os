package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.PaymentPlanRequest;
import com.stringstack.talentos.dto.fees.PaymentPlanResponse;

import java.util.List;

public interface PaymentPlanService {

    PaymentPlanResponse createPaymentPlan(PaymentPlanRequest request);

    List<PaymentPlanResponse> getAllPaymentPlans();

    PaymentPlanResponse getPaymentPlanById(Long id);

    PaymentPlanResponse updatePaymentPlan(Long id, PaymentPlanRequest request);

    void deactivatePaymentPlan(Long id);

    void activatePaymentPlan(Long id);
}