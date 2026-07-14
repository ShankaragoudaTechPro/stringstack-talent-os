package com.stringstack.talentos.dto.attendance;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {

    @NotBlank
    private String attendanceCode;

    @NotNull
    private Long enrollmentId;

    @NotNull
    private LocalDate attendanceDate;

    @NotBlank
    private String status;

    private String remarks;

    @NotNull
    private Boolean active;

}