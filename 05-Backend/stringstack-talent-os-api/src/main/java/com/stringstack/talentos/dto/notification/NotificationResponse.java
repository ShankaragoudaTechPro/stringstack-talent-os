package com.stringstack.talentos.dto.notification;

import com.stringstack.talentos.constants.NotificationPriority;
import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.constants.NotificationType;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;

    // =====================================================
    // BASIC
    // =====================================================

    private String notificationCode;

    private String title;

    private String message;

    private String recipientType;

    // =====================================================
    // STUDENT
    // =====================================================

    private Long studentId;

    private String studentName;

    // =====================================================
    // TRAINER
    // =====================================================

    private Long trainerId;

    private String trainerName;

    // =====================================================
    // COMPANY
    // =====================================================

    private Long companyId;

    private String companyName;

    // =====================================================
    // TYPE / PRIORITY / SOURCE
    // =====================================================

    private NotificationType type;

    private NotificationPriority priority;

    private NotificationSource source;

    private Long sourceId;

    // =====================================================
    // STATUS
    // =====================================================

    private Boolean read;

    private String status;

    private Boolean active;

    // =====================================================
    // DATES
    // =====================================================

    private LocalDateTime sentAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime scheduledAt;

    private LocalDateTime expiresAt;
}