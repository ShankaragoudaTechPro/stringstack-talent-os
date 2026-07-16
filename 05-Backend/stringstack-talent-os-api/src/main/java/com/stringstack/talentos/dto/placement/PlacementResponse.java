package com.stringstack.talentos.dto.placement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementResponse {

    private Long id;

    private String placementCode;

    private Long enrollmentId;

    private String studentName;

    private Long placementDriveId;

    private String driveTitle;

    private Long companyId;

    private String companyName;

    private String interviewStatus;

    private String selectionStatus;

    private Double offeredPackage;

    private LocalDate joiningDate;

    private String remarks;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}