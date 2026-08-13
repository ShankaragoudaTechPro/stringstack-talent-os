package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.fees.PaymentPlanRequest;
import com.stringstack.talentos.dto.fees.PaymentPlanResponse;
import com.stringstack.talentos.entity.PaymentPlan;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.PaymentPlanRepository;
import com.stringstack.talentos.service.PaymentPlanService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentPlanServiceImpl implements PaymentPlanService {

    private final PaymentPlanRepository paymentPlanRepository;

    @Override
    public PaymentPlanResponse createPaymentPlan(PaymentPlanRequest request) {

        if (paymentPlanRepository.existsByPlanNameIgnoreCase(
                request.getPlanName().trim())) {

            throw new IllegalStateException(
                    "Payment plan already exists: " + request.getPlanName());
        }

        PaymentPlan paymentPlan = PaymentPlan.builder()
                .planName(request.getPlanName().trim())
                .numberOfInstallments(request.getNumberOfInstallments())
                .extraCharge(valueOrZero(request.getExtraCharge()))
                .active(true)
                .build();

        PaymentPlan saved = paymentPlanRepository.save(paymentPlan);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentPlanResponse> getAllPaymentPlans() {

        return paymentPlanRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentPlanResponse getPaymentPlanById(Long id) {

        PaymentPlan paymentPlan = paymentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment plan not found with id: " + id));

        return mapToResponse(paymentPlan);
    }

    @Override
    public PaymentPlanResponse updatePaymentPlan(
            Long id,
            PaymentPlanRequest request) {

        PaymentPlan paymentPlan = paymentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment plan not found with id: " + id));

        String planName = request.getPlanName().trim();

        if (paymentPlanRepository
                .existsByPlanNameIgnoreCaseAndIdNot(planName, id)) {

            throw new IllegalStateException(
                    "Another payment plan already exists with name: "
                            + planName);
        }

        paymentPlan.setPlanName(planName);
        paymentPlan.setNumberOfInstallments(
                request.getNumberOfInstallments());
        paymentPlan.setExtraCharge(
                valueOrZero(request.getExtraCharge()));

        PaymentPlan updated = paymentPlanRepository.save(paymentPlan);

        return mapToResponse(updated);
    }

    @Override
    public void deactivatePaymentPlan(Long id) {

        PaymentPlan paymentPlan = paymentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment plan not found with id: " + id));

        paymentPlan.setActive(false);

        paymentPlanRepository.save(paymentPlan);
    }

    @Override
    public void activatePaymentPlan(Long id) {

        PaymentPlan paymentPlan = paymentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment plan not found with id: " + id));

        paymentPlan.setActive(true);

        paymentPlanRepository.save(paymentPlan);
    }

    private BigDecimal valueOrZero(BigDecimal value) {

        return value == null
                ? BigDecimal.ZERO
                : value;
    }

    private PaymentPlanResponse mapToResponse(
            PaymentPlan paymentPlan) {

        return PaymentPlanResponse.builder()
                .id(paymentPlan.getId())
                .planName(paymentPlan.getPlanName())
                .numberOfInstallments(
                        paymentPlan.getNumberOfInstallments())
                .extraCharge(paymentPlan.getExtraCharge())
                .active(paymentPlan.getActive())
                .build();
    }
}