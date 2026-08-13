package com.stringstack.talentos.dto.fees;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentPlanRequest {

    @NotBlank(message = "Plan name is required")
    private String planName;

    @NotNull(message = "Number of installments is required")
    @Min(value = 1, message = "Number of installments must be at least 1")
    private Integer numberOfInstallments;

    @NotNull(message = "Extra charge is required")
    @DecimalMin(value = "0.00", message = "Extra charge cannot be negative")
    private BigDecimal extraCharge;
}