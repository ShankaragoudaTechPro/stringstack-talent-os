package com.stringstack.talentos.dto.batch;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchRequest {

    @NotBlank
    private String batchCode;

    @NotBlank
    private String batchName;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Integer capacity;

    @NotNull
    private Boolean active;

    @NotNull
    private Long courseId;

    @NotNull
    private Long trainerId;

}