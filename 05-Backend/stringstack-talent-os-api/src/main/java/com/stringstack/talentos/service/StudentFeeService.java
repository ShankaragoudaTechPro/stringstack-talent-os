package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.fees.CreateStudentFeeRequest;
import com.stringstack.talentos.dto.fees.StudentFeeResponse;

import java.util.List;

public interface StudentFeeService {

    StudentFeeResponse createStudentFee(
            CreateStudentFeeRequest request);

    List<StudentFeeResponse> getAllStudentFees();

    StudentFeeResponse getStudentFeeById(Long id);

    StudentFeeResponse getStudentFeeByEnrollmentId(
            Long enrollmentId);
}