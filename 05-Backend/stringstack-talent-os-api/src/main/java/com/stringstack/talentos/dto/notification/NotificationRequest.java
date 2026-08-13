package com.stringstack.talentos.dto.notification;

import com.stringstack.talentos.constants.NotificationPriority;
import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.constants.NotificationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    @NotBlank
    private String notificationCode;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotBlank
    private String recipientType;

    private Long studentId;

    private Long trainerId;

    private Long companyId;

    @NotNull
    private NotificationType type;

    @NotNull
    private NotificationPriority priority;

    @NotNull
    private NotificationSource source;

    private Long sourceId;

    private LocalDateTime sentAt;

    private LocalDateTime scheduledAt;

    private LocalDateTime expiresAt;

    @NotBlank
    private String status;

    @NotNull
    private Boolean active;
}