package com.stringstack.talentos.dto.batch;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchResponse {

    private Long id;

    private String batchCode;

    private String batchName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer capacity;

    private Boolean active;

    private Long courseId;

    private String courseName;

    private Long trainerId;

    private String trainerName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}