package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Enrollment;
import com.stringstack.talentos.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentFeeRepository
        extends JpaRepository<StudentFee, Long> {

    boolean existsByEnrollment(Enrollment enrollment);

    Optional<StudentFee> findByEnrollment(Enrollment enrollment);
}