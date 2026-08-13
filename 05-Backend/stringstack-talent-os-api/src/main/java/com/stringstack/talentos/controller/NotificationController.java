package com.stringstack.talentos.controller;

import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;
import com.stringstack.talentos.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // =====================================================
    // CREATE
    // =====================================================

    @PostMapping
    public ResponseEntity<NotificationResponse> create(
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        notificationService
                                .createNotification(request)
                );
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {

        return ResponseEntity.ok(
                notificationService.getAllNotifications()
        );
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationById(id)
        );
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody NotificationRequest request) {

        return ResponseEntity.ok(
                notificationService
                        .updateNotification(id, request)
        );
    }

    // =====================================================
    // DELETE
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // STUDENT NOTIFICATIONS
    // =====================================================

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<NotificationResponse>>
    getByStudent(@PathVariable Long studentId) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByStudent(studentId)
        );
    }

    // =====================================================
    // STUDENT UNREAD
    // =====================================================

    @GetMapping("/student/{studentId}/unread")
    public ResponseEntity<List<NotificationResponse>>
    getUnreadByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                notificationService
                        .getUnreadNotificationsByStudent(
                                studentId)
        );
    }

    // =====================================================
    // MARK ALL STUDENT READ
    // =====================================================

    @PatchMapping("/student/{studentId}/read-all")
    public ResponseEntity<Void> markAllStudentRead(
            @PathVariable Long studentId) {

        notificationService
                .markAllStudentNotificationsAsRead(
                        studentId
                );

        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // TRAINER NOTIFICATIONS
    // =====================================================

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<NotificationResponse>>
    getByTrainer(@PathVariable Long trainerId) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsByTrainer(trainerId)
        );
    }

    // =====================================================
    // TRAINER UNREAD
    // =====================================================

    @GetMapping("/trainer/{trainerId}/unread")
    public ResponseEntity<List<NotificationResponse>>
    getUnreadByTrainer(
            @PathVariable Long trainerId) {

        return ResponseEntity.ok(
                notificationService
                        .getUnreadNotificationsByTrainer(
                                trainerId)
        );
    }

    // =====================================================
    // MARK ALL TRAINER READ
    // =====================================================

    @PatchMapping("/trainer/{trainerId}/read-all")
    public ResponseEntity<Void> markAllTrainerRead(
            @PathVariable Long trainerId) {

        notificationService
                .markAllTrainerNotificationsAsRead(
                        trainerId
                );

        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // MARK ONE READ
    // =====================================================

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                notificationService.markAsRead(id)
        );
    }

    // =====================================================
    // SOURCE
    // =====================================================

    @GetMapping("/source/{source}/{sourceId}")
    public ResponseEntity<List<NotificationResponse>>
    getBySource(
            @PathVariable NotificationSource source,
            @PathVariable Long sourceId) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsBySource(
                                source,
                                sourceId
                        )
        );
    }
}