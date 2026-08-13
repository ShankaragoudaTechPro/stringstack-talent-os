package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class StudentFeeResponse {

    private Long id;

    private Long enrollmentId;

    private Long feeStructureId;

    private Long paymentPlanId;

    private String paymentPlanName;

    private BigDecimal baseFee;

    private BigDecimal planExtraCharge;

    private BigDecimal discount;

    private BigDecimal finalFee;

    private BigDecimal paidAmount;

    private BigDecimal pendingAmount;

    private String status;

    private LocalDate assignedDate;
}