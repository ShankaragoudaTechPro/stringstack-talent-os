package com.stringstack.talentos.entity;

import com.stringstack.talentos.constants.NotificationPriority;
import com.stringstack.talentos.constants.NotificationSource;
import com.stringstack.talentos.constants.NotificationType;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =====================================================
    // NOTIFICATION CODE
    // =====================================================

    @Column(
            name = "notification_code",
            nullable = false,
            unique = true,
            length = 100
    )
    private String notificationCode;

    // =====================================================
    // CONTENT
    // =====================================================

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 1000)
    private String message;

    // =====================================================
    // RECIPIENT TYPE
    // =====================================================

    @Column(
            name = "recipient_type",
            nullable = false,
            length = 30
    )
    private String recipientType;

    // =====================================================
    // RECIPIENTS
    // =====================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // =====================================================
    // TYPE
    // =====================================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationType type;

    // =====================================================
    // PRIORITY
    // =====================================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationPriority priority;

    // =====================================================
    // SOURCE
    // =====================================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NotificationSource source;

    @Column(name = "source_id")
    private Long sourceId;

    // =====================================================
    // STATUS
    // =====================================================

    @Column(name = "is_read", nullable = false)
    private Boolean read;
    

    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false)
    private Boolean active;

    // =====================================================
    // DATES
    // =====================================================

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    // =====================================================
    // PRE PERSIST
    // =====================================================

    @PrePersist
    protected void onCreate() {

        if (read == null) {
            read = false;
        }

        if (priority == null) {
            priority = NotificationPriority.MEDIUM;
        }

        if (active == null) {
            active = true;
        }

        if (status == null) {
            status = "SENT";
        }

        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }
    }
}