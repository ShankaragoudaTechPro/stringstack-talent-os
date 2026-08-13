package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.FeeInstallment;
import com.stringstack.talentos.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeInstallmentRepository
        extends JpaRepository<FeeInstallment, Long> {

    List<FeeInstallment>
    findByStudentFeeOrderByInstallmentNumberAsc(
            StudentFee studentFee
    );

    boolean existsByStudentFee(StudentFee studentFee);
}