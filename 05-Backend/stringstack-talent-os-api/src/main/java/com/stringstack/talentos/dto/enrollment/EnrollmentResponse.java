package com.stringstack.talentos.dto.enrollment;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private Long id;

    private String enrollmentCode;

    private Long studentId;

    private String studentName;

    private Long batchId;

    private String batchName;

    private LocalDate enrollmentDate;

    private String status;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}