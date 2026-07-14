package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.enrollment.EnrollmentRequest;
import com.stringstack.talentos.dto.enrollment.EnrollmentResponse;
import com.stringstack.talentos.entity.Enrollment;

public class EnrollmentMapper {

    private EnrollmentMapper() {
    }

    public static Enrollment toEntity(EnrollmentRequest request) {

        return Enrollment.builder()
                .enrollmentCode(request.getEnrollmentCode())
                .enrollmentDate(request.getEnrollmentDate())
                .status(request.getStatus())
                .active(request.getActive())
                .build();
    }

    public static EnrollmentResponse toResponse(Enrollment enrollment) {

        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .enrollmentCode(enrollment.getEnrollmentCode())
                .studentId(enrollment.getStudent().getId())
                .studentName(
                        enrollment.getStudent().getFirstName() + " " +
                                enrollment.getStudent().getLastName())
                .batchId(enrollment.getBatch().getId())
                .batchName(enrollment.getBatch().getBatchName())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .status(enrollment.getStatus())
                .active(enrollment.getActive())
                .createdAt(enrollment.getCreatedAt())
                .updatedAt(enrollment.getUpdatedAt())
                .build();
    }

}