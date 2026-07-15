package com.stringstack.talentos.mapper;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveRequest;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveResponse;
import com.stringstack.talentos.entity.PlacementDrive;

public class PlacementDriveMapper {

    private PlacementDriveMapper() {
    }

    public static PlacementDrive toEntity(
            PlacementDriveRequest request) {

        return PlacementDrive.builder()
                .driveCode(request.getDriveCode())
                .driveTitle(request.getDriveTitle())
                .jobRole(request.getJobRole())
                .jobLocation(request.getJobLocation())
                .packageOffered(request.getPackageOffered())
                .eligibilityCriteria(request.getEligibilityCriteria())
                .driveDate(request.getDriveDate())
                .lastDateToApply(request.getLastDateToApply())
                .status(request.getStatus())
                .active(request.getActive())
                .build();
    }

    public static PlacementDriveResponse toResponse(
            PlacementDrive drive) {

        return PlacementDriveResponse.builder()
                .id(drive.getId())
                .driveCode(drive.getDriveCode())
                .driveTitle(drive.getDriveTitle())
                .companyId(drive.getCompany().getId())
                .companyName(drive.getCompany().getCompanyName())
                .jobRole(drive.getJobRole())
                .jobLocation(drive.getJobLocation())
                .packageOffered(drive.getPackageOffered())
                .eligibilityCriteria(drive.getEligibilityCriteria())
                .driveDate(drive.getDriveDate())
                .lastDateToApply(drive.getLastDateToApply())
                .status(drive.getStatus())
                .active(drive.getActive())
                .createdAt(drive.getCreatedAt())
                .updatedAt(drive.getUpdatedAt())
                .build();
    }
}