package com.stringstack.talentos.dto.fees;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FeeStructureResponse {

    private Long id;

    private Long courseId;

    private String courseName;

    private BigDecimal registrationFee;

    private BigDecimal tuitionFee;

    private BigDecimal materialFee;

    private BigDecimal examFee;

    private BigDecimal discount;

    private BigDecimal gstPercentage;

    private BigDecimal totalFee;

    private Boolean active;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
}