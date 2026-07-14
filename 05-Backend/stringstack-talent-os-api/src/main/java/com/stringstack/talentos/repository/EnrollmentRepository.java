package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByEnrollmentCode(String enrollmentCode);

    Optional<Enrollment> findByEnrollmentCode(String enrollmentCode);

    boolean existsByStudentIdAndBatchId(Long studentId, Long batchId);

}