package com.stringstack.talentos.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponse {

    private Long id;

    private String resumeCode;

    private Long studentId;

    private String studentName;

    private String title;

    private String fileName;

    private String fileType;

    private String filePath;

    private LocalDate uploadedAt;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}