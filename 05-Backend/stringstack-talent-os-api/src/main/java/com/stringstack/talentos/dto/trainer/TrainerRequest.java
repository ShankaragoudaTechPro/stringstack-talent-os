package com.stringstack.talentos.dto.trainer;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerRequest {

    @NotBlank
    private String trainerCode;

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
    private String specialization;

    @NotNull
    private Double experience;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    private Boolean active;

    @NotNull
    private Long userId;
}