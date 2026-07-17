package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.resume.ResumeRequest;
import com.stringstack.talentos.dto.resume.ResumeResponse;
import com.stringstack.talentos.entity.Resume;
import com.stringstack.talentos.entity.Student;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.ResumeMapper;
import com.stringstack.talentos.repository.ResumeRepository;
import com.stringstack.talentos.repository.StudentRepository;
import com.stringstack.talentos.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentRepository studentRepository;

    @Override
    public ResumeResponse createResume(ResumeRequest request) {

        if (resumeRepository.existsByResumeCode(request.getResumeCode())) {
            throw new DuplicateResourceException("Resume code already exists.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        Resume resume = ResumeMapper.toEntity(request);
        resume.setStudent(student);

        Resume savedResume = resumeRepository.save(resume);

        return ResumeMapper.toResponse(savedResume);
    }

    @Override
    public List<ResumeResponse> getAllResumes() {

        return resumeRepository.findAll()
                .stream()
                .map(ResumeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeResponse getResumeById(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found."));

        return ResumeMapper.toResponse(resume);
    }

    @Override
    public ResumeResponse updateResume(Long id, ResumeRequest request) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found."));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        resume.setResumeCode(request.getResumeCode());
        resume.setStudent(student);
        resume.setTitle(request.getTitle());
        resume.setFileName(request.getFileName());
        resume.setFileType(request.getFileType());
        resume.setFilePath(request.getFilePath());
        resume.setUploadedAt(request.getUploadedAt());
        resume.setActive(request.getActive());

        Resume updatedResume = resumeRepository.save(resume);

        return ResumeMapper.toResponse(updatedResume);
    }

    @Override
    public void deleteResume(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found."));

        resumeRepository.delete(resume);
    }
}