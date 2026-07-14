package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.enrollment.EnrollmentRequest;
import com.stringstack.talentos.dto.enrollment.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(EnrollmentRequest request);

    List<EnrollmentResponse> getAllEnrollments();

    EnrollmentResponse getEnrollmentById(Long id);

    EnrollmentResponse updateEnrollment(Long id,
                                        EnrollmentRequest request);

    void deleteEnrollment(Long id);

}