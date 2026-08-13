package com.stringstack.talentos.dto.fees;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateInstallmentRequest {

    @NotNull(message = "Student fee ID is required")
    private Long studentFeeId;

    @NotNull(message = "First due date is required")
    private LocalDate firstDueDate;

    @NotNull(message = "Interval in months is required")
    @Min(
            value = 1,
            message = "Interval in months must be at least 1"
    )
    private Integer intervalInMonths;
}