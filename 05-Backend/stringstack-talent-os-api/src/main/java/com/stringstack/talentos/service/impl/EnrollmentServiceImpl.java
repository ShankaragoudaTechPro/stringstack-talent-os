package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.enrollment.EnrollmentRequest;
import com.stringstack.talentos.dto.enrollment.EnrollmentResponse;
import com.stringstack.talentos.entity.Batch;
import com.stringstack.talentos.entity.Enrollment;
import com.stringstack.talentos.entity.Student;
import com.stringstack.talentos.mapper.EnrollmentMapper;
import com.stringstack.talentos.repository.BatchRepository;
import com.stringstack.talentos.repository.EnrollmentRepository;
import com.stringstack.talentos.repository.StudentRepository;
import com.stringstack.talentos.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest request) {

        if (enrollmentRepository.existsByEnrollmentCode(request.getEnrollmentCode())) {
            throw new RuntimeException("Enrollment Code already exists.");
        }

        if (enrollmentRepository.existsByStudentIdAndBatchId(
                request.getStudentId(),
                request.getBatchId())) {

            throw new RuntimeException("Student is already enrolled in this batch.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found."));

        Batch batch = batchRepository.findById(request.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found."));

        Enrollment enrollment = EnrollmentMapper.toEntity(request);

        enrollment.setStudent(student);
        enrollment.setBatch(batch);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(savedEnrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found."));

        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponse updateEnrollment(Long id,
                                               EnrollmentRequest request) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found."));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found."));

        Batch batch = batchRepository.findById(request.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found."));

        enrollment.setEnrollmentCode(request.getEnrollmentCode());
        enrollment.setEnrollmentDate(request.getEnrollmentDate());
        enrollment.setStatus(request.getStatus());
        enrollment.setActive(request.getActive());
        enrollment.setStudent(student);
        enrollment.setBatch(batch);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found."));

        enrollmentRepository.delete(enrollment);
    }
}