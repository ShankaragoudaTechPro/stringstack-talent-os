package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.student.StudentRequest;
import com.stringstack.talentos.dto.student.StudentResponse;
import com.stringstack.talentos.entity.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static Student toEntity(StudentRequest request) {

        return Student.builder()
                .studentCode(request.getStudentCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .qualification(request.getQualification())
                .college(request.getCollege())
                .passingYear(request.getPassingYear())
                .percentage(request.getPercentage())
                .skillSet(request.getSkillSet())
                .active(request.getActive())
                .build();
    }

    public static StudentResponse toResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .studentCode(student.getStudentCode())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .qualification(student.getQualification())
                .college(student.getCollege())
                .passingYear(student.getPassingYear())
                .percentage(student.getPercentage())
                .skillSet(student.getSkillSet())
                .active(student.getActive())
                .userId(student.getUser().getId())
                .userEmail(student.getUser().getEmail())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}