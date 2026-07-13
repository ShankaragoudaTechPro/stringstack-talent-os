package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.student.StudentRequest;
import com.stringstack.talentos.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);
}