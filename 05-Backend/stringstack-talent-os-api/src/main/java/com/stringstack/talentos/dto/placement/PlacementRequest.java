package com.stringstack.talentos.dto.placement;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementRequest {

    private String placementCode;

    private Long enrollmentId;

    private Long placementDriveId;

    private Long companyId;

    private String interviewStatus;

    private String selectionStatus;

    private Double offeredPackage;

    private LocalDate joiningDate;

    private String remarks;

    private Boolean active;
}