package com.stringstack.talentos.service;

import com.stringstack.talentos.constants.NotificationPriority;
import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.constants.NotificationType;
import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {

    // CRUD

    NotificationResponse createNotification(
            NotificationRequest request
    );

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long id);

    NotificationResponse updateNotification(
            Long id,
            NotificationRequest request
    );

    void deleteNotification(Long id);


    // Student

    List<NotificationResponse> getNotificationsByStudent(
            Long studentId
    );

    List<NotificationResponse> getUnreadNotificationsByStudent(
            Long studentId
    );

    void markAllStudentNotificationsAsRead(
            Long studentId
    );


    // Trainer

    List<NotificationResponse> getNotificationsByTrainer(
            Long trainerId
    );

    List<NotificationResponse> getUnreadNotificationsByTrainer(
            Long trainerId
    );

    void markAllTrainerNotificationsAsRead(
            Long trainerId
    );


    // Read

    NotificationResponse markAsRead(
            Long notificationId
    );


    // Automatic student notification

    NotificationResponse createStudentSystemNotification(
            Long studentId,
            String title,
            String message,
            NotificationType type,
            NotificationPriority priority,
            NotificationSource source,
            Long sourceId
    );


    // Automatic trainer notification

    NotificationResponse createTrainerSystemNotification(
            Long trainerId,
            String title,
            String message,
            NotificationType type,
            NotificationPriority priority,
            NotificationSource source,
            Long sourceId
    );


    // Source

    List<NotificationResponse> getNotificationsBySource(
            NotificationSource source,
            Long sourceId
    );
}