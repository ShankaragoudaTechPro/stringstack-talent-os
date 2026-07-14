package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.trainer.TrainerRequest;
import com.stringstack.talentos.dto.trainer.TrainerResponse;

import java.util.List;

public interface TrainerService {

    TrainerResponse createTrainer(TrainerRequest request);

    List<TrainerResponse> getAllTrainers();

    TrainerResponse getTrainerById(Long id);

    TrainerResponse updateTrainer(Long id, TrainerRequest request);

    void deleteTrainer(Long id);

}