package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.FeeStructureRequest;
import com.stringstack.talentos.dto.fees.FeeStructureResponse;

import java.util.List;

public interface FeeStructureService {

    FeeStructureResponse createFeeStructure(FeeStructureRequest request);

    List<FeeStructureResponse> getAllFeeStructures();

    FeeStructureResponse getFeeStructureById(Long id);

    FeeStructureResponse updateFeeStructure(Long id, FeeStructureRequest request);

    void deactivateFeeStructure(Long id);

    void activateFeeStructure(Long id);
}