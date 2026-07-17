package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.resume.ResumeRequest;
import com.stringstack.talentos.dto.resume.ResumeResponse;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(ResumeRequest request);

    List<ResumeResponse> getAllResumes();

    ResumeResponse getResumeById(Long id);

    ResumeResponse updateResume(Long id, ResumeRequest request);

    void deleteResume(Long id);

}