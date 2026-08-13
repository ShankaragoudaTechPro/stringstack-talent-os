package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.FeePayment;
import com.stringstack.talentos.entity.FeeInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeePaymentRepository
        extends JpaRepository<FeePayment, Long> {

    List<FeePayment> findByInstallmentOrderByPaymentDateDesc(
            FeeInstallment installment);

    boolean existsByTransactionReference(
            String transactionReference);
}