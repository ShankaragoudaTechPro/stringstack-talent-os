package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.constants.FeeStatus;
import com.stringstack.talentos.dto.fees.CreateStudentFeeRequest;
import com.stringstack.talentos.dto.fees.StudentFeeResponse;
import com.stringstack.talentos.entity.Enrollment;
import com.stringstack.talentos.entity.FeeStructure;
import com.stringstack.talentos.entity.PaymentPlan;
import com.stringstack.talentos.entity.StudentFee;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.EnrollmentRepository;
import com.stringstack.talentos.repository.FeeStructureRepository;
import com.stringstack.talentos.repository.PaymentPlanRepository;
import com.stringstack.talentos.repository.StudentFeeRepository;
import com.stringstack.talentos.service.StudentFeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentFeeServiceImpl
        implements StudentFeeService {

    private final StudentFeeRepository studentFeeRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final FeeStructureRepository feeStructureRepository;

    private final PaymentPlanRepository paymentPlanRepository;

    @Override
    public StudentFeeResponse createStudentFee(
            CreateStudentFeeRequest request) {

        Enrollment enrollment =
                enrollmentRepository.findById(request.getEnrollmentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found with id: "
                                                + request.getEnrollmentId()));

        FeeStructure feeStructure =
                feeStructureRepository.findById(
                                request.getFeeStructureId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Fee structure not found with id: "
                                                + request.getFeeStructureId()));

        PaymentPlan paymentPlan =
                paymentPlanRepository.findById(
                                request.getPaymentPlanId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment plan not found with id: "
                                                + request.getPaymentPlanId()));

        if (!feeStructure.getActive()) {
            throw new IllegalStateException(
                    "Selected fee structure is inactive");
        }

        if (!paymentPlan.getActive()) {
            throw new IllegalStateException(
                    "Selected payment plan is inactive");
        }

        if (studentFeeRepository.existsByEnrollment(enrollment)) {
            throw new IllegalStateException(
                    "Fee is already assigned to this enrollment");
        }

        BigDecimal baseFee =
                feeStructure.getTotalFee();

        BigDecimal planExtraCharge =
                paymentPlan.getExtraCharge();

        BigDecimal discount =
                request.getDiscount() == null
                        ? BigDecimal.ZERO
                        : request.getDiscount();

        BigDecimal finalFee =
                baseFee
                        .add(planExtraCharge)
                        .subtract(discount);

        if (finalFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException(
                    "Final fee cannot be negative");
        }

        StudentFee studentFee = StudentFee.builder()
                .enrollment(enrollment)
                .feeStructure(feeStructure)
                .paymentPlan(paymentPlan)
                .baseFee(baseFee)
                .planExtraCharge(planExtraCharge)
                .discount(discount)
                .finalFee(finalFee)
                .paidAmount(BigDecimal.ZERO)
                .pendingAmount(finalFee)
                .status(FeeStatus.PENDING)
                .assignedDate(LocalDate.now())
                .build();

        StudentFee saved =
                studentFeeRepository.save(studentFee);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentFeeResponse> getAllStudentFees() {

        return studentFeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentFeeResponse getStudentFeeById(Long id) {

        StudentFee studentFee =
                studentFeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student fee not found with id: "
                                                + id));

        return mapToResponse(studentFee);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentFeeResponse getStudentFeeByEnrollmentId(
            Long enrollmentId) {

        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found with id: "
                                                + enrollmentId));

        StudentFee studentFee =
                studentFeeRepository.findByEnrollment(enrollment)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Fee not assigned for enrollment id: "
                                                + enrollmentId));

        return mapToResponse(studentFee);
    }

    private StudentFeeResponse mapToResponse(
            StudentFee studentFee) {

        return StudentFeeResponse.builder()
                .id(studentFee.getId())
                .enrollmentId(
                        studentFee.getEnrollment().getId())
                .feeStructureId(
                        studentFee.getFeeStructure().getId())
                .paymentPlanId(
                        studentFee.getPaymentPlan().getId())
                .paymentPlanName(
                        studentFee.getPaymentPlan().getPlanName())
                .baseFee(studentFee.getBaseFee())
                .planExtraCharge(
                        studentFee.getPlanExtraCharge())
                .discount(studentFee.getDiscount())
                .finalFee(studentFee.getFinalFee())
                .paidAmount(studentFee.getPaidAmount())
                .pendingAmount(studentFee.getPendingAmount())
                .status(studentFee.getStatus().name())
                .assignedDate(studentFee.getAssignedDate())
                .build();
    }
}