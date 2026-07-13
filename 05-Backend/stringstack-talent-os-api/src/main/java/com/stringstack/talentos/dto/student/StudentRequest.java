package com.stringstack.talentos.dto.student;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    @NotBlank
    private String studentCode;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String qualification;

    @NotBlank
    private String college;

    @NotNull
    private Integer passingYear;

    @NotNull
    private Double percentage;

    private String skillSet;

    @NotNull
    private Boolean active;

    @NotNull
    private Long userId;
}