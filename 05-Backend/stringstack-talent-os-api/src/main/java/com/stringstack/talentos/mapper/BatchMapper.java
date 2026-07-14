package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.batch.BatchRequest;
import com.stringstack.talentos.dto.batch.BatchResponse;
import com.stringstack.talentos.entity.Batch;

public class BatchMapper {

    private BatchMapper() {
    }

    public static Batch toEntity(BatchRequest request) {

        return Batch.builder()
                .batchCode(request.getBatchCode())
                .batchName(request.getBatchName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .capacity(request.getCapacity())
                .active(request.getActive())
                .build();
    }

    public static BatchResponse toResponse(Batch batch) {

        return BatchResponse.builder()
                .id(batch.getId())
                .batchCode(batch.getBatchCode())
                .batchName(batch.getBatchName())
                .startDate(batch.getStartDate())
                .endDate(batch.getEndDate())
                .capacity(batch.getCapacity())
                .active(batch.getActive())
                .courseId(batch.getCourse().getId())
                .courseName(batch.getCourse().getCourseName())
                .trainerId(batch.getTrainer().getId())
                .trainerName(
                        batch.getTrainer().getFirstName() + " "
                                + batch.getTrainer().getLastName())
                .createdAt(batch.getCreatedAt())
                .updatedAt(batch.getUpdatedAt())
                .build();
    }

}