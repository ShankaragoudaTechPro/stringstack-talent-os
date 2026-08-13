package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Course;
import com.stringstack.talentos.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {

    Optional<FeeStructure> findByCourseAndActiveTrue(Course course);

    boolean existsByCourseAndActiveTrue(Course course);
}