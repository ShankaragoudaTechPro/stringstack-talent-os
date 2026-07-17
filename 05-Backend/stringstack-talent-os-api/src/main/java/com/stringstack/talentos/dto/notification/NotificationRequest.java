package com.stringstack.talentos.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate sentAt;

    @NotBlank
    private String status;

    @NotNull
    private Boolean active;

}