package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.placement.PlacementRequest;
import com.stringstack.talentos.dto.placement.PlacementResponse;
import com.stringstack.talentos.entity.Placement;

public class PlacementMapper {

    private PlacementMapper() {
    }

    public static Placement toEntity(PlacementRequest request) {

        return Placement.builder()
                .placementCode(request.getPlacementCode())
                .interviewStatus(request.getInterviewStatus())
                .selectionStatus(request.getSelectionStatus())
                .offeredPackage(request.getOfferedPackage())
                .joiningDate(request.getJoiningDate())
                .remarks(request.getRemarks())
                .active(request.getActive())
                .build();
    }

    public static PlacementResponse toResponse(Placement placement) {

        return PlacementResponse.builder()
                .id(placement.getId())
                .placementCode(placement.getPlacementCode())
                .enrollmentId(placement.getEnrollment().getId())
                .studentName(
                        placement.getEnrollment().getStudent().getFirstName()
                                + " "
                                + placement.getEnrollment().getStudent().getLastName())
                .placementDriveId(placement.getPlacementDrive().getId())
                .driveTitle(placement.getPlacementDrive().getDriveTitle())
                .companyId(placement.getCompany().getId())
                .companyName(placement.getCompany().getCompanyName())
                .interviewStatus(placement.getInterviewStatus())
                .selectionStatus(placement.getSelectionStatus())
                .offeredPackage(placement.getOfferedPackage())
                .joiningDate(placement.getJoiningDate())
                .remarks(placement.getRemarks())
                .active(placement.getActive())
                .createdAt(placement.getCreatedAt())
                .updatedAt(placement.getUpdatedAt())
                .build();
    }
}