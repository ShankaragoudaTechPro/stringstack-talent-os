package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.constants.NotificationPriority;
import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.constants.NotificationType;
import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.entity.Notification;
import com.stringstack.talentos.entity.Student;
import com.stringstack.talentos.entity.Trainer;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.repository.NotificationRepository;
import com.stringstack.talentos.repository.StudentRepository;
import com.stringstack.talentos.repository.TrainerRepository;
import com.stringstack.talentos.service.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;
    private final TrainerRepository trainerRepository;
    private final CompanyRepository companyRepository;

    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public NotificationResponse createNotification(
            NotificationRequest request) {

        if (notificationRepository.existsByNotificationCode(
                request.getNotificationCode())) {

            throw new DuplicateResourceException(
                    "Notification Code already exists."
            );
        }

        Notification notification = new Notification();

        notification.setNotificationCode(
                request.getNotificationCode()
        );

        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setRecipientType(
                request.getRecipientType()
        );

        notification.setType(request.getType());
        notification.setPriority(request.getPriority());
        notification.setSource(request.getSource());
        notification.setSourceId(request.getSourceId());

        notification.setSentAt(request.getSentAt());
        notification.setScheduledAt(request.getScheduledAt());
        notification.setExpiresAt(request.getExpiresAt());

        notification.setRead(false);
        notification.setStatus(request.getStatus());
        notification.setActive(request.getActive());

        setRecipients(notification, request);

        Notification saved =
                notificationRepository.save(notification);

        return mapToResponse(saved);
    }

    // =====================================================
    // GET ALL
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =====================================================
    // GET BY ID
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse getNotificationById(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."
                                ));

        return mapToResponse(notification);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public NotificationResponse updateNotification(
            Long id,
            NotificationRequest request) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."
                                ));

        if (!notification.getNotificationCode()
                .equals(request.getNotificationCode())
                && notificationRepository.existsByNotificationCode(
                request.getNotificationCode())) {

            throw new DuplicateResourceException(
                    "Notification Code already exists."
            );
        }

        notification.setNotificationCode(
                request.getNotificationCode()
        );

        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());

        notification.setRecipientType(
                request.getRecipientType()
        );

        notification.setType(request.getType());
        notification.setPriority(request.getPriority());
        notification.setSource(request.getSource());
        notification.setSourceId(request.getSourceId());

        notification.setSentAt(request.getSentAt());
        notification.setScheduledAt(request.getScheduledAt());
        notification.setExpiresAt(request.getExpiresAt());

        notification.setStatus(request.getStatus());
        notification.setActive(request.getActive());

        // Clear old recipients
        notification.setStudent(null);
        notification.setTrainer(null);
        notification.setCompany(null);

        // Set new recipients
        setRecipients(notification, request);

        Notification updated =
                notificationRepository.save(notification);

        return mapToResponse(updated);
    }

    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteNotification(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."
                                ));

        notificationRepository.delete(notification);
    }

    // =====================================================
    // STUDENT NOTIFICATIONS
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotificationsByStudent(
            Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student not found."
            );
        }

        return notificationRepository
                .findByStudentIdOrderByCreatedAtDesc(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse>
    getUnreadNotificationsByStudent(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student not found."
            );
        }

        return notificationRepository
                .findByStudentIdAndReadFalseOrderByCreatedAtDesc(
                        studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void markAllStudentNotificationsAsRead(
            Long studentId) {

        List<Notification> notifications =
                notificationRepository
                        .findByStudentIdAndReadFalseOrderByCreatedAtDesc(
                                studentId);

        notifications.forEach(
                notification -> notification.setRead(true)
        );

        notificationRepository.saveAll(notifications);
    }

    // =====================================================
    // TRAINER NOTIFICATIONS
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotificationsByTrainer(
            Long trainerId) {

        if (!trainerRepository.existsById(trainerId)) {
            throw new ResourceNotFoundException(
                    "Trainer not found."
            );
        }

        return notificationRepository
                .findByTrainerIdOrderByCreatedAtDesc(trainerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse>
    getUnreadNotificationsByTrainer(Long trainerId) {

        if (!trainerRepository.existsById(trainerId)) {
            throw new ResourceNotFoundException(
                    "Trainer not found."
            );
        }

        return notificationRepository
                .findByTrainerIdAndReadFalseOrderByCreatedAtDesc(
                        trainerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void markAllTrainerNotificationsAsRead(
            Long trainerId) {

        List<Notification> notifications =
                notificationRepository
                        .findByTrainerIdAndReadFalseOrderByCreatedAtDesc(
                                trainerId);

        notifications.forEach(
                notification -> notification.setRead(true)
        );

        notificationRepository.saveAll(notifications);
    }

    // =====================================================
    // MARK ONE READ
    // =====================================================

    @Override
    public NotificationResponse markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."
                                ));

        notification.setRead(true);

        return mapToResponse(
                notificationRepository.save(notification)
        );
    }

    // =====================================================
    // SYSTEM → STUDENT
    // =====================================================

    @Override
    public NotificationResponse createStudentSystemNotification(
            Long studentId,
            String title,
            String message,
            NotificationType type,
            NotificationPriority priority,
            NotificationSource source,
            Long sourceId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found."
                                ));

        Notification notification =
                Notification.builder()
                        .notificationCode(
                                "SYS-STU-" +
                                        System.currentTimeMillis()
                        )
                        .title(title)
                        .message(message)
                        .recipientType("STUDENT")
                        .student(student)
                        .type(type)
                        .priority(priority)
                        .source(source)
                        .sourceId(sourceId)
                        .read(false)
                        .status("SENT")
                        .active(true)
                        .sentAt(LocalDateTime.now())
                        .build();

        return mapToResponse(
                notificationRepository.save(notification)
        );
    }

    // =====================================================
    // SYSTEM → TRAINER
    // =====================================================

    @Override
    public NotificationResponse createTrainerSystemNotification(
            Long trainerId,
            String title,
            String message,
            NotificationType type,
            NotificationPriority priority,
            NotificationSource source,
            Long sourceId) {

        Trainer trainer =
                trainerRepository.findById(trainerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Trainer not found."
                                ));

        Notification notification =
                Notification.builder()
                        .notificationCode(
                                "SYS-TRN-" +
                                        System.currentTimeMillis()
                        )
                        .title(title)
                        .message(message)
                        .recipientType("TRAINER")
                        .trainer(trainer)
                        .type(type)
                        .priority(priority)
                        .source(source)
                        .sourceId(sourceId)
                        .read(false)
                        .status("SENT")
                        .active(true)
                        .sentAt(LocalDateTime.now())
                        .build();

        return mapToResponse(
                notificationRepository.save(notification)
        );
    }

    // =====================================================
    // SOURCE
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotificationsBySource(
            NotificationSource source,
            Long sourceId) {

        return notificationRepository
                .findBySourceAndSourceId(source, sourceId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =====================================================
    // RECIPIENT MAPPING
    // =====================================================

    private void setRecipients(
            Notification notification,
            NotificationRequest request) {

        int recipientCount = 0;

        if (request.getStudentId() != null) {

            Student student =
                    studentRepository.findById(
                                    request.getStudentId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Student not found."
                                    ));

            notification.setStudent(student);
            recipientCount++;
        }

        if (request.getTrainerId() != null) {

            Trainer trainer =
                    trainerRepository.findById(
                                    request.getTrainerId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Trainer not found."
                                    ));

            notification.setTrainer(trainer);
            recipientCount++;
        }

        if (request.getCompanyId() != null) {

            Company company =
                    companyRepository.findById(
                                    request.getCompanyId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Company not found."
                                    ));

            notification.setCompany(company);
            recipientCount++;
        }

        if (recipientCount == 0) {

            throw new IllegalArgumentException(
                    "At least one recipient is required."
            );
        }
    }

    // =====================================================
    // MAPPER
    // =====================================================
    private NotificationResponse mapToResponse(
            Notification notification) {

        Long studentId = null;
        String studentName = null;

        Long trainerId = null;
        String trainerName = null;

        Long companyId = null;
        String companyName = null;

        if (notification.getStudent() != null) {

            studentId = notification.getStudent().getId();

            studentName =
                    notification.getStudent().getFirstName()
                            + " "
                            + notification.getStudent().getLastName();
        }

        if (notification.getTrainer() != null) {

            trainerId = notification.getTrainer().getId();

            trainerName =
                    notification.getTrainer().getFirstName()
                            + " "
                            + notification.getTrainer().getLastName();
        }

        if (notification.getCompany() != null) {

            companyId = notification.getCompany().getId();

            companyName =
                    notification.getCompany().getCompanyName();
        }

        return NotificationResponse.builder()

                .id(notification.getId())

                .notificationCode(
                        notification.getNotificationCode())

                .title(notification.getTitle())

                .message(notification.getMessage())

                .recipientType(
                        notification.getRecipientType())

                .studentId(studentId)

                .studentName(studentName)

                .trainerId(trainerId)

                .trainerName(trainerName)

                .companyId(companyId)

                .companyName(companyName)

                .type(notification.getType())

                .priority(notification.getPriority())

                .source(notification.getSource())

                .sourceId(notification.getSourceId())

                .read(notification.getRead())

                .status(notification.getStatus())

                .active(notification.getActive())

                .sentAt(notification.getSentAt())

                .createdAt(notification.getCreatedAt())

                .scheduledAt(notification.getScheduledAt())

                .expiresAt(notification.getExpiresAt())

                .build();
    }
}