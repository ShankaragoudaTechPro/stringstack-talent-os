package com.stringstack.talentos.dto.enrollment;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequest {

    @NotBlank
    private String enrollmentCode;

    @NotNull
    private Long studentId;

    @NotNull
    private Long batchId;

    @NotNull
    private LocalDate enrollmentDate;

    @NotBlank
    private String status;

    @NotNull
    private Boolean active;

}