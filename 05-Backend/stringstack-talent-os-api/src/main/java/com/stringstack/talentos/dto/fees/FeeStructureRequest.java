package com.stringstack.talentos.dto.fees;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FeeStructureRequest {

    @NotNull
    private Long courseId;

    @DecimalMin("0.00")
    private BigDecimal registrationFee;

    @DecimalMin("0.00")
    private BigDecimal tuitionFee;

    @DecimalMin("0.00")
    private BigDecimal materialFee;

    @DecimalMin("0.00")
    private BigDecimal examFee;

    @DecimalMin("0.00")
    private BigDecimal discount;

    @DecimalMin("0.00")
    private BigDecimal gstPercentage;

    @NotNull
    private LocalDate effectiveFrom;
}