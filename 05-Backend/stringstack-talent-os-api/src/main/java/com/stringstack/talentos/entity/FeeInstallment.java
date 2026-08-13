package com.stringstack.talentos.entity;

import com.stringstack.talentos.constants.FeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "fee_installments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_student_fee_installment",
                        columnNames = {
                                "student_fee_id",
                                "installment_number"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_fee_id",
            nullable = false
    )
    private StudentFee studentFee;

    @Column(
            name = "installment_number",
            nullable = false
    )
    private Integer installmentNumber;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal paidAmount;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal pendingAmount;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FeeStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}