package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByBatchCode(String batchCode);

    Optional<Batch> findByBatchCode(String batchCode);

    @Query("""
    SELECT COUNT(e) > 0
    FROM CalendarEvent e
    WHERE e.batch.id = :batchId
    AND e.status <> com.stringstack.talentos.constants.EventStatus.CANCELLED
    AND e.startDateTime < :endDateTime
    AND e.endDateTime > :startDateTime
    AND (:eventId IS NULL OR e.id <> :eventId)
""")
    boolean existsBatchConflict(
            Long batchId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            Long eventId
    );

}