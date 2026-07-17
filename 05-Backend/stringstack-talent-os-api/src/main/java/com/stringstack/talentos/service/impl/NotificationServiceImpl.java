package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.notification.NotificationRequest;
import com.stringstack.talentos.dto.notification.NotificationResponse;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.entity.Notification;
import com.stringstack.talentos.entity.Student;
import com.stringstack.talentos.entity.Trainer;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.NotificationMapper;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.repository.NotificationRepository;
import com.stringstack.talentos.repository.StudentRepository;
import com.stringstack.talentos.repository.TrainerRepository;
import com.stringstack.talentos.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;
    private final TrainerRepository trainerRepository;
    private final CompanyRepository companyRepository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {

        if (notificationRepository.existsByNotificationCode(request.getNotificationCode())) {
            throw new DuplicateResourceException("Notification Code already exists.");
        }

        Notification notification = NotificationMapper.toEntity(request);

        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found."));
            notification.setStudent(student);
        }

        if (request.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(request.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));
            notification.setTrainer(trainer);
        }

        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found."));
            notification.setCompany(company);
        }

        Notification saved = notificationRepository.save(notification);

        return NotificationMapper.toResponse(saved);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(NotificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found."));

        return NotificationMapper.toResponse(notification);
    }

    @Override
    public NotificationResponse updateNotification(Long id, NotificationRequest request) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found."));

        notification.setNotificationCode(request.getNotificationCode());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setRecipientType(request.getRecipientType());
        notification.setSentAt(request.getSentAt());
        notification.setStatus(request.getStatus());
        notification.setActive(request.getActive());

        notification.setStudent(null);
        notification.setTrainer(null);
        notification.setCompany(null);

        if (request.getStudentId() != null) {
            notification.setStudent(
                    studentRepository.findById(request.getStudentId())
                            .orElseThrow(() -> new ResourceNotFoundException("Student not found."))
            );
        }

        if (request.getTrainerId() != null) {
            notification.setTrainer(
                    trainerRepository.findById(request.getTrainerId())
                            .orElseThrow(() -> new RuntimeException("Trainer not found."))
            );
        }

        if (request.getCompanyId() != null) {
            notification.setCompany(
                    companyRepository.findById(request.getCompanyId())
                            .orElseThrow(() -> new ResourceNotFoundException("Company not found."))
            );
        }

        Notification updated = notificationRepository.save(notification);

        return NotificationMapper.toResponse(updated);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found."));

        notificationRepository.delete(notification);
    }
}