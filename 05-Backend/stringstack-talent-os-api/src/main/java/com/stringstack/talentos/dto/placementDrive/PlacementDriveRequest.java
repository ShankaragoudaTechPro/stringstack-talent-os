package com.stringstack.talentos.dto.placementDrive;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementDriveRequest {

    @NotBlank
    private String driveCode;

    @NotBlank
    private String driveTitle;

    @NotNull
    private Long companyId;

    @NotBlank
    private String jobRole;

    @NotBlank
    private String jobLocation;

    @NotNull
    private Double packageOffered;

    @NotBlank
    private String eligibilityCriteria;

    @NotNull
    private LocalDate driveDate;

    @NotNull
    private LocalDate lastDateToApply;

    @NotBlank
    private String status;

    @NotNull
    private Boolean active;
}