package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByBatchCode(String batchCode);

    Optional<Batch> findByBatchCode(String batchCode);

}