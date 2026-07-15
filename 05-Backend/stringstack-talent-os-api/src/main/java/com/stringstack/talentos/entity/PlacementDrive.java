package com.stringstack.talentos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "placement_drives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drive_code", nullable = false, unique = true)
    private String driveCode;

    @Column(name = "drive_title", nullable = false)
    private String driveTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private String jobLocation;

    @Column(nullable = false)
    private Double packageOffered;

    @Column(nullable = false, length = 500)
    private String eligibilityCriteria;

    @Column(nullable = false)
    private LocalDate driveDate;

    @Column(nullable = false)
    private LocalDate lastDateToApply;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}