package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

}