package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FeeReceiptResponse {

    private Long paymentId;

    private String receiptNumber;

    private LocalDate paymentDate;

    private String instituteName;

    private String instituteAddress;

    private String institutePhone;

    private String instituteEmail;

    private String studentName;

    private String studentCode;

    private String courseName;

    private String batchName;

    private Integer installmentNumber;

    private BigDecimal totalFee;

    private BigDecimal amountPaid;

    private BigDecimal totalPaid;

    private BigDecimal pendingAmount;

    private String paymentMode;

    private String transactionReference;

    private String remarks;
}