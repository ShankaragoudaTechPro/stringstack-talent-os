package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarEventRepository
        extends JpaRepository<CalendarEvent, Long> {

    List<CalendarEvent>
    findByStartDateTimeBetweenOrderByStartDateTimeAsc(
            LocalDateTime start,
            LocalDateTime end
    );

    List<CalendarEvent>
    findByBatchIdOrderByStartDateTimeAsc(
            Long batchId
    );

    List<CalendarEvent>
    findByTrainerIdOrderByStartDateTimeAsc(
            Long trainerId
    );

    List<CalendarEvent>
    findByCompanyIdOrderByStartDateTimeAsc(
            Long companyId
    );


    // ==========================================
    // TRAINER CONFLICT
    // ==========================================

    @Query("""
        SELECT COUNT(e) > 0
        FROM CalendarEvent e
        WHERE e.trainer.id = :trainerId
          AND e.status <> com.stringstack.talentos.constants.EventStatus.CANCELLED
          AND e.startDateTime < :endDateTime
          AND e.endDateTime > :startDateTime
          AND (:eventId IS NULL OR e.id <> :eventId)
    """)
    boolean existsTrainerConflict(
            @Param("trainerId") Long trainerId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("eventId") Long eventId
    );


    // ==========================================
    // BATCH CONFLICT
    // ==========================================

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
            @Param("batchId") Long batchId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("eventId") Long eventId
    );

    @Query("""
    SELECT e
    FROM CalendarEvent e
    WHERE e.reminderEnabled = true
      AND e.reminderSent = false
      AND e.status = com.stringstack.talentos.constants.EventStatus.SCHEDULED
      AND e.startDateTime <= :reminderTime
      AND e.startDateTime > :currentTime
""")
    List<CalendarEvent> findEventsForReminder(
            @Param("currentTime") LocalDateTime currentTime,
            @Param("reminderTime") LocalDateTime reminderTime
    );
}