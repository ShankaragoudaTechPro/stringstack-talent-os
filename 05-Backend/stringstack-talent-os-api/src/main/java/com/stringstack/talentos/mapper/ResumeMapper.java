package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.resume.ResumeRequest;
import com.stringstack.talentos.dto.resume.ResumeResponse;
import com.stringstack.talentos.entity.Resume;

public class ResumeMapper {

    private ResumeMapper() {
    }

    public static Resume toEntity(ResumeRequest request) {

        return Resume.builder()
                .resumeCode(request.getResumeCode())
                .title(request.getTitle())
                .fileName(request.getFileName())
                .fileType(request.getFileType())
                .filePath(request.getFilePath())
                .uploadedAt(request.getUploadedAt())
                .active(request.getActive())
                .build();
    }

    public static ResumeResponse toResponse(Resume resume) {

        return ResumeResponse.builder()
                .id(resume.getId())
                .resumeCode(resume.getResumeCode())
                .studentId(resume.getStudent().getId())
                .studentName(
                        resume.getStudent().getFirstName() + " " +
                                resume.getStudent().getLastName())
                .title(resume.getTitle())
                .fileName(resume.getFileName())
                .fileType(resume.getFileType())
                .filePath(resume.getFilePath())
                .uploadedAt(resume.getUploadedAt())
                .active(resume.getActive())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

}