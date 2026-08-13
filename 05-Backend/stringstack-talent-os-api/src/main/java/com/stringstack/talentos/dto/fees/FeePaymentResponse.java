package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FeePaymentResponse {

    private Long id;

    private Long studentFeeId;

    private Long installmentId;

    private Integer installmentNumber;

    private BigDecimal amount;

    private String paymentMode;

    private String transactionReference;

    private String receiptNumber;

    private LocalDate paymentDate;

    private String remarks;
}