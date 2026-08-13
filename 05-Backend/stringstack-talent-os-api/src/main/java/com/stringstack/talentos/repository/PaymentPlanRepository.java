package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {

    boolean existsByPlanNameIgnoreCase(String planName);

    Optional<PaymentPlan> findByPlanNameIgnoreCase(String planName);

    boolean existsByPlanNameIgnoreCaseAndIdNot(String planName, Long id);
}