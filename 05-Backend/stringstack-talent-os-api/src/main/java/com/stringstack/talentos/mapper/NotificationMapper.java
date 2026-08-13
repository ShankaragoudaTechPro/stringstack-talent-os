package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;
import com.stringstack.talentos.entity.Notification;

public class NotificationMapper {

    private NotificationMapper() {
    }

    // =====================================================
    // REQUEST → ENTITY
    // =====================================================

    public static Notification toEntity(
            NotificationRequest request) {

        return Notification.builder()

                .notificationCode(
                        request.getNotificationCode())

                .title(
                        request.getTitle())

                .message(
                        request.getMessage())

                .recipientType(
                        request.getRecipientType())

                .type(
                        request.getType())

                .priority(
                        request.getPriority())

                .source(
                        request.getSource())

                .sourceId(
                        request.getSourceId())

                .sentAt(
                        request.getSentAt())

                .scheduledAt(
                        request.getScheduledAt())

                .expiresAt(
                        request.getExpiresAt())

                .read(false)

                .status(
                        request.getStatus())

                .active(
                        request.getActive())

                .build();
    }

    // =====================================================
    // ENTITY → RESPONSE
    // =====================================================

    public static NotificationResponse toResponse(
            Notification notification) {

        return NotificationResponse.builder()

                .id(
                        notification.getId())

                .notificationCode(
                        notification.getNotificationCode())

                .title(
                        notification.getTitle())

                .message(
                        notification.getMessage())

                .recipientType(
                        notification.getRecipientType())

                // ==========================
                // STUDENT
                // ==========================

                .studentId(
                        notification.getStudent() != null
                                ? notification.getStudent().getId()
                                : null)

                .studentName(
                        notification.getStudent() != null
                                ? notification.getStudent().getFirstName()
                                + " "
                                + notification.getStudent().getLastName()
                                : null)

                // ==========================
                // TRAINER
                // ==========================

                .trainerId(
                        notification.getTrainer() != null
                                ? notification.getTrainer().getId()
                                : null)

                .trainerName(
                        notification.getTrainer() != null
                                ? notification.getTrainer().getFirstName()
                                + " "
                                + notification.getTrainer().getLastName()
                                : null)

                // ==========================
                // COMPANY
                // ==========================

                .companyId(
                        notification.getCompany() != null
                                ? notification.getCompany().getId()
                                : null)

                .companyName(
                        notification.getCompany() != null
                                ? notification.getCompany().getCompanyName()
                                : null)

                // ==========================
                // TYPE
                // ==========================

                .type(
                        notification.getType())

                .priority(
                        notification.getPriority())

                .source(
                        notification.getSource())

                .sourceId(
                        notification.getSourceId())

                // ==========================
                // STATUS
                // ==========================

                .read(
                        notification.getRead())

                .status(
                        notification.getStatus())

                .active(
                        notification.getActive())

                // ==========================
                // DATES
                // ==========================

                .sentAt(
                        notification.getSentAt())

                .createdAt(
                        notification.getCreatedAt())

                .updatedAt(
                        notification.getUpdatedAt())

                .scheduledAt(
                        notification.getScheduledAt())

                .expiresAt(
                        notification.getExpiresAt())

                .build();
    }
}