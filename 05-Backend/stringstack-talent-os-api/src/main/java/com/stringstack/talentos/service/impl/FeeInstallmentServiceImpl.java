package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.constants.FeeStatus;
import com.stringstack.talentos.dto.fees.CreateInstallmentRequest;
import com.stringstack.talentos.dto.fees.FeeInstallmentResponse;
import com.stringstack.talentos.entity.FeeInstallment;
import com.stringstack.talentos.entity.PaymentPlan;
import com.stringstack.talentos.entity.StudentFee;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.FeeInstallmentRepository;
import com.stringstack.talentos.repository.StudentFeeRepository;
import com.stringstack.talentos.service.FeeInstallmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeeInstallmentServiceImpl
        implements FeeInstallmentService {

    private final FeeInstallmentRepository feeInstallmentRepository;

    private final StudentFeeRepository studentFeeRepository;

    @Override
    public List<FeeInstallmentResponse> generateInstallments(
            CreateInstallmentRequest request) {

        StudentFee studentFee =
                studentFeeRepository.findById(
                                request.getStudentFeeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student fee not found with id: "
                                                + request.getStudentFeeId()
                                ));

        // Prevent duplicate installment generation
        if (feeInstallmentRepository.existsByStudentFee(studentFee)) {
            throw new IllegalStateException(
                    "Installments are already generated for this student fee"
            );
        }

        PaymentPlan paymentPlan =
                studentFee.getPaymentPlan();

        int numberOfInstallments =
                paymentPlan.getNumberOfInstallments();

        if (numberOfInstallments <= 0) {
            throw new IllegalStateException(
                    "Payment plan must contain at least one installment"
            );
        }

        BigDecimal finalFee =
                studentFee.getFinalFee();

        /*
         * Divide total fee among installments.
         *
         * Example:
         *
         * Final Fee = 50,000
         * Installments = 2
         *
         * Installment 1 = 25,000
         * Installment 2 = 25,000
         */

        BigDecimal baseInstallmentAmount =
                finalFee.divide(
                        BigDecimal.valueOf(numberOfInstallments),
                        2,
                        RoundingMode.DOWN
                );

        List<FeeInstallment> installments =
                new ArrayList<>();

        BigDecimal allocatedAmount =
                BigDecimal.ZERO;

        for (int i = 1; i <= numberOfInstallments; i++) {

            BigDecimal installmentAmount;

            /*
             * Last installment receives any rounding difference.
             */
            if (i == numberOfInstallments) {

                installmentAmount =
                        finalFee.subtract(allocatedAmount);

            } else {

                installmentAmount =
                        baseInstallmentAmount;
            }

            FeeInstallment installment =
                    FeeInstallment.builder()
                            .studentFee(studentFee)
                            .installmentNumber(i)
                            .amount(installmentAmount)
                            .paidAmount(BigDecimal.ZERO)
                            .pendingAmount(installmentAmount)
                            .dueDate(
                                    request.getFirstDueDate()
                                            .plusMonths(
                                                    (long) (i - 1)
                                                            * request.getIntervalInMonths()
                                            )
                            )
                            .status(FeeStatus.PENDING)
                            .build();

            installments.add(installment);

            allocatedAmount =
                    allocatedAmount.add(installmentAmount);
        }

        List<FeeInstallment> savedInstallments =
                feeInstallmentRepository.saveAll(
                        installments
                );

        return savedInstallments
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeeInstallmentResponse>
    getInstallmentsByStudentFee(
            Long studentFeeId) {

        StudentFee studentFee =
                studentFeeRepository.findById(studentFeeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student fee not found with id: "
                                                + studentFeeId
                                ));

        return feeInstallmentRepository
                .findByStudentFeeOrderByInstallmentNumberAsc(
                        studentFee
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FeeInstallmentResponse getInstallmentById(
            Long id) {

        FeeInstallment installment =
                feeInstallmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Installment not found with id: "
                                                + id
                                ));

        return mapToResponse(installment);
    }

    private FeeInstallmentResponse mapToResponse(
            FeeInstallment installment) {

        return FeeInstallmentResponse.builder()
                .id(installment.getId())
                .studentFeeId(
                        installment.getStudentFee().getId()
                )
                .installmentNumber(
                        installment.getInstallmentNumber()
                )
                .amount(
                        installment.getAmount()
                )
                .paidAmount(
                        installment.getPaidAmount()
                )
                .pendingAmount(
                        installment.getPendingAmount()
                )
                .dueDate(
                        installment.getDueDate()
                )
                .paymentDate(
                        installment.getPaymentDate()
                )
                .status(
                        installment.getStatus().name()
                )
                .build();
    }
}