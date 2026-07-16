package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.placement.PlacementRequest;
import com.stringstack.talentos.dto.placement.PlacementResponse;

import java.util.List;

public interface PlacementService {

    PlacementResponse createPlacement(PlacementRequest request);

    List<PlacementResponse> getAllPlacements();

    PlacementResponse getPlacementById(Long id);

    PlacementResponse updatePlacement(Long id,
                                      PlacementRequest request);

    void deletePlacement(Long id);

}