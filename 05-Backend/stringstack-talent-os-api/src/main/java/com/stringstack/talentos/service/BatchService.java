package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.batch.BatchRequest;
import com.stringstack.talentos.dto.batch.BatchResponse;

import java.util.List;

public interface BatchService {

    BatchResponse createBatch(BatchRequest request);

    List<BatchResponse> getAllBatches();

    BatchResponse getBatchById(Long id);

    BatchResponse updateBatch(Long id, BatchRequest request);

    void deleteBatch(Long id);

}