package com.stringstack.talentos.dto.student;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;

    private String studentCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String gender;

    private LocalDate dateOfBirth;

    private String qualification;

    private String college;

    private Integer passingYear;

    private Double percentage;

    private String skillSet;

    private Boolean active;

    private Long userId;

    private String userEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}