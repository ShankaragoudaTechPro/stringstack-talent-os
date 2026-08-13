package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FeeInstallmentResponse {

    private Long id;

    private Long studentFeeId;

    private Integer installmentNumber;

    private BigDecimal amount;

    private BigDecimal paidAmount;

    private BigDecimal pendingAmount;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    private String status;
}