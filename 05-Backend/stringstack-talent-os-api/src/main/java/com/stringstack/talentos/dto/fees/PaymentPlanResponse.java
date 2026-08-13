package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentPlanResponse {

    private Long id;

    private String planName;

    private Integer numberOfInstallments;

    private BigDecimal extraCharge;

    private Boolean active;
}