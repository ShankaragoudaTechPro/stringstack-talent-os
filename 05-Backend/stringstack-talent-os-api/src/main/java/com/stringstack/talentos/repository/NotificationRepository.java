package com.stringstack.talentos.repository;

import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    boolean existsByNotificationCode(
            String notificationCode
    );

    List<Notification>
    findByStudentIdOrderByCreatedAtDesc(
            Long studentId
    );

    List<Notification>
    findByTrainerIdOrderByCreatedAtDesc(
            Long trainerId
    );

    List<Notification>
    findByCompanyIdOrderByCreatedAtDesc(
            Long companyId
    );

    List<Notification>
    findByStudentIdAndReadFalseOrderByCreatedAtDesc(
            Long studentId
    );

    List<Notification>
    findByTrainerIdAndReadFalseOrderByCreatedAtDesc(
            Long trainerId
    );

    List<Notification>
    findByCompanyIdAndReadFalseOrderByCreatedAtDesc(
            Long companyId
    );

    List<Notification>
    findBySourceAndSourceId(
            NotificationSource source,
            Long sourceId
    );
}