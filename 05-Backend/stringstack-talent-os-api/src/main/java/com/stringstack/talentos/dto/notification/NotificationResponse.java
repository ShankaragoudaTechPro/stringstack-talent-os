package com.stringstack.talentos.dto.notification;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;

    private String notificationCode;

    private String title;

    private String message;

    private String recipientType;

    private Long studentId;

    private String studentName;

    private Long trainerId;

    private String trainerName;

    private Long companyId;

    private String companyName;

    private LocalDate sentAt;

    private String status;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}