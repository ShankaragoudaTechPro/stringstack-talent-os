package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.trainer.TrainerRequest;
import com.stringstack.talentos.dto.trainer.TrainerResponse;
import com.stringstack.talentos.entity.Trainer;

public class TrainerMapper {

    private TrainerMapper() {
    }

    public static Trainer toEntity(TrainerRequest request) {

        return Trainer.builder()
                .trainerCode(request.getTrainerCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .specialization(request.getSpecialization())
                .experience(request.getExperience())
                .joiningDate(request.getJoiningDate())
                .active(request.getActive())
                .build();
    }

    public static TrainerResponse toResponse(Trainer trainer) {

        return TrainerResponse.builder()
                .id(trainer.getId())
                .trainerCode(trainer.getTrainerCode())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .email(trainer.getEmail())
                .phone(trainer.getPhone())
                .specialization(trainer.getSpecialization())
                .experience(trainer.getExperience())
                .joiningDate(trainer.getJoiningDate())
                .active(trainer.getActive())
                .userId(trainer.getUser().getId())
                .userEmail(trainer.getUser().getEmail())
                .createdAt(trainer.getCreatedAt())
                .updatedAt(trainer.getUpdatedAt())
                .build();
    }

}