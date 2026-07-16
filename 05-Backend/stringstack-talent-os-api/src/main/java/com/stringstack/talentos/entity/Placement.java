package com.stringstack.talentos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "placements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="placement_code", nullable=false, unique=true)
    private String placementCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="enrollment_id", nullable=false)
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="placement_drive_id", nullable=false)
    private PlacementDrive placementDrive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @Column(nullable=false)
    private String interviewStatus;

    @Column(nullable=false)
    private String selectionStatus;

    @Column(nullable=false)
    private Double offeredPackage;

    private LocalDate joiningDate;

    private String remarks;

    @Column(nullable=false)
    private Boolean active;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}