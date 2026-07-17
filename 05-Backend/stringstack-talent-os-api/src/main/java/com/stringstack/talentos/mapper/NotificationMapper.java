package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;
import com.stringstack.talentos.entity.Notification;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static Notification toEntity(NotificationRequest request) {

        return Notification.builder()
                .notificationCode(request.getNotificationCode())
                .title(request.getTitle())
                .message(request.getMessage())
                .recipientType(request.getRecipientType())
                .sentAt(request.getSentAt())
                .status(request.getStatus())
                .active(request.getActive())
                .build();
    }

    public static NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .notificationCode(notification.getNotificationCode())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .recipientType(notification.getRecipientType())

                .studentId(notification.getStudent() != null ? notification.getStudent().getId() : null)
                .studentName(notification.getStudent() != null
                        ? notification.getStudent().getFirstName() + " " + notification.getStudent().getLastName()
                        : null)

                .trainerId(notification.getTrainer() != null ? notification.getTrainer().getId() : null)
                .trainerName(notification.getTrainer() != null
                        ? notification.getTrainer().getFirstName() + " " + notification.getTrainer().getLastName()
                        : null)

                .companyId(notification.getCompany() != null ? notification.getCompany().getId() : null)
                .companyName(notification.getCompany() != null
                        ? notification.getCompany().getCompanyName()
                        : null)

                .sentAt(notification.getSentAt())
                .status(notification.getStatus())
                .active(notification.getActive())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();
    }
}