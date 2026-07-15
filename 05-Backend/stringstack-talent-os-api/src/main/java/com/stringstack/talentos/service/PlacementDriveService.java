package com.stringstack.talentos.service;



import com.stringstack.talentos.dto.placementDrive.PlacementDriveRequest;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveResponse;

import java.util.List;

public interface PlacementDriveService {

    PlacementDriveResponse createPlacementDrive(
            PlacementDriveRequest request);

    List<PlacementDriveResponse> getAllPlacementDrives();

    PlacementDriveResponse getPlacementDriveById(Long id);

    PlacementDriveResponse updatePlacementDrive(
            Long id,
            PlacementDriveRequest request);

    void deletePlacementDrive(Long id);
}