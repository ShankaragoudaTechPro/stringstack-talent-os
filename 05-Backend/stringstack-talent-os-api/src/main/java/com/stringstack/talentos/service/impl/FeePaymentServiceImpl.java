package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.constants.FeeStatus;
import com.stringstack.talentos.dto.fees.CreatePaymentRequest;
import com.stringstack.talentos.dto.fees.FeePaymentResponse;
import com.stringstack.talentos.entity.FeeInstallment;
import com.stringstack.talentos.entity.FeePayment;
import com.stringstack.talentos.entity.StudentFee;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.FeeInstallmentRepository;
import com.stringstack.talentos.repository.FeePaymentRepository;
import com.stringstack.talentos.repository.StudentFeeRepository;
import com.stringstack.talentos.service.FeePaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeePaymentServiceImpl
        implements FeePaymentService {

    private final FeePaymentRepository feePaymentRepository;

    private final FeeInstallmentRepository feeInstallmentRepository;

    private final StudentFeeRepository studentFeeRepository;

    @Override
    public FeePaymentResponse createPayment(
            CreatePaymentRequest request) {

        FeeInstallment installment =
                feeInstallmentRepository.findById(
                                request.getInstallmentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Installment not found with id: "
                                                + request.getInstallmentId()));

        StudentFee studentFee =
                installment.getStudentFee();

        BigDecimal paymentAmount =
                request.getAmount();

        BigDecimal installmentPending =
                installment.getPendingAmount();

        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException(
                    "Payment amount must be greater than zero");
        }

        if (paymentAmount.compareTo(installmentPending) > 0) {
            throw new IllegalStateException(
                    "Payment amount cannot be greater than installment pending amount");
        }

        if (request.getTransactionReference() != null
                && !request.getTransactionReference().isBlank()
                && feePaymentRepository.existsByTransactionReference(
                request.getTransactionReference())) {

            throw new IllegalStateException(
                    "Transaction reference already exists");
        }

        String receiptNumber =
                generateReceiptNumber();

        FeePayment payment = FeePayment.builder()
                .studentFee(studentFee)
                .installment(installment)
                .amount(paymentAmount)
                .paymentMode(request.getPaymentMode())
                .transactionReference(
                        request.getTransactionReference())
                .receiptNumber(receiptNumber)
                .paymentDate(LocalDate.now())
                .remarks(request.getRemarks())
                .build();

        FeePayment saved =
                feePaymentRepository.save(payment);

        /*
         * Update installment
         */

        BigDecimal newInstallmentPaid =
                installment.getPaidAmount()
                        .add(paymentAmount);

        BigDecimal newInstallmentPending =
                installment.getAmount()
                        .subtract(newInstallmentPaid);

        installment.setPaidAmount(newInstallmentPaid);
        installment.setPendingAmount(newInstallmentPending);
        installment.setPaymentDate(LocalDate.now());

        if (newInstallmentPending.compareTo(BigDecimal.ZERO) == 0) {

            installment.setStatus(FeeStatus.PAID);

        } else {

            installment.setStatus(
                    FeeStatus.PARTIALLY_PAID);
        }

        feeInstallmentRepository.save(installment);

        /*
         * Update Student Fee
         */

        BigDecimal newStudentPaid =
                studentFee.getPaidAmount()
                        .add(paymentAmount);

        BigDecimal newStudentPending =
                studentFee.getFinalFee()
                        .subtract(newStudentPaid);

        studentFee.setPaidAmount(newStudentPaid);
        studentFee.setPendingAmount(newStudentPending);

        if (newStudentPending.compareTo(BigDecimal.ZERO) == 0) {

            studentFee.setStatus(FeeStatus.PAID);

        } else {

            studentFee.setStatus(
                    FeeStatus.PARTIALLY_PAID);
        }

        studentFeeRepository.save(studentFee);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FeePaymentResponse getPaymentById(Long id) {

        FeePayment payment =
                feePaymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found with id: "
                                                + id));

        return mapToResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeePaymentResponse>
    getPaymentsByInstallment(Long installmentId) {

        FeeInstallment installment =
                feeInstallmentRepository.findById(installmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Installment not found with id: "
                                                + installmentId));

        return feePaymentRepository
                .findByInstallmentOrderByPaymentDateDesc(
                        installment)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeePaymentResponse>
    getPaymentsByStudentFee(Long studentFeeId) {

        StudentFee studentFee =
                studentFeeRepository.findById(studentFeeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student fee not found with id: "
                                                + studentFeeId));

        return feePaymentRepository
                .findAll()
                .stream()
                .filter(payment ->
                        payment.getStudentFee()
                                .getId()
                                .equals(studentFee.getId()))
                .map(this::mapToResponse)
                .toList();
    }

    private String generateReceiptNumber() {

        String date =
                LocalDate.now()
                        .format(
                                DateTimeFormatter
                                        .ofPattern("yyyyMMdd"));

        long sequence =
                feePaymentRepository.count() + 1;

        return String.format(
                "RCPT-%s-%05d",
                date,
                sequence);
    }

    private FeePaymentResponse mapToResponse(
            FeePayment payment) {

        return FeePaymentResponse.builder()
                .id(payment.getId())
                .studentFeeId(
                        payment.getStudentFee().getId())
                .installmentId(
                        payment.getInstallment().getId())
                .installmentNumber(
                        payment.getInstallment()
                                .getInstallmentNumber())
                .amount(payment.getAmount())
                .paymentMode(
                        payment.getPaymentMode().name())
                .transactionReference(
                        payment.getTransactionReference())
                .receiptNumber(
                        payment.getReceiptNumber())
                .paymentDate(
                        payment.getPaymentDate())
                .remarks(payment.getRemarks())
                .build();
    }
}