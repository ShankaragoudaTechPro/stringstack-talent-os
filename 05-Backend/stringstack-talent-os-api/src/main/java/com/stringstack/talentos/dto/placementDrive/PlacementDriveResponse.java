package com.stringstack.talentos.dto.placementDrive;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementDriveResponse {

    private Long id;

    private String driveCode;

    private String driveTitle;

    private Long companyId;

    private String companyName;

    private String jobRole;

    private String jobLocation;

    private Double packageOffered;

    private String eligibilityCriteria;

    private LocalDate driveDate;

    private LocalDate lastDateToApply;

    private String status;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}