package com.stringstack.talentos.dto.attendance;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {

    private Long id;

    private String attendanceCode;

    private Long enrollmentId;

    private String studentName;

    private String batchName;

    private LocalDate attendanceDate;

    private String status;

    private String remarks;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}