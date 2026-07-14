package com.stringstack.talentos.dto.trainer;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerResponse {

    private Long id;

    private String trainerCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String specialization;

    private Double experience;

    private LocalDate joiningDate;

    private Boolean active;

    private Long userId;

    private String userEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}