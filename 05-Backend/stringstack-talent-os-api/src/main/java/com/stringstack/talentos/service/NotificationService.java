package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {

    NotificationResponse createNotification(NotificationRequest request);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long id);

    NotificationResponse updateNotification(Long id, NotificationRequest request);

    void deleteNotification(Long id);

}