package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    boolean existsByResumeCode(String resumeCode);

    Optional<Resume> findByResumeCode(String resumeCode);

}