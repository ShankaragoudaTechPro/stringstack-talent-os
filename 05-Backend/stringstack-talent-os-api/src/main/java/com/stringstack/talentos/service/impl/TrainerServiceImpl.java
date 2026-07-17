package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.trainer.TrainerRequest;
import com.stringstack.talentos.dto.trainer.TrainerResponse;
import com.stringstack.talentos.entity.Trainer;
import com.stringstack.talentos.entity.User;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.TrainerMapper;
import com.stringstack.talentos.repository.TrainerRepository;
import com.stringstack.talentos.repository.UserRepository;
import com.stringstack.talentos.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    @Override
    public TrainerResponse createTrainer(TrainerRequest request) {

        if (trainerRepository.existsByTrainerCode(request.getTrainerCode()))
            throw new DuplicateResourceException("Trainer Code already exists.");

        if (trainerRepository.existsByEmail(request.getEmail()))
            throw new DuplicateResourceException("Email already exists.");

        if (trainerRepository.existsByPhone(request.getPhone()))
            throw new DuplicateResourceException("Phone already exists.");

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        Trainer trainer = TrainerMapper.toEntity(request);

        trainer.setUser(user);

        Trainer savedTrainer = trainerRepository.save(trainer);

        return TrainerMapper.toResponse(savedTrainer);
    }

    @Override
    public List<TrainerResponse> getAllTrainers() {

        return trainerRepository.findAll()
                .stream()
                .map(TrainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerResponse getTrainerById(Long id) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));

        return TrainerMapper.toResponse(trainer);
    }

    @Override
    public TrainerResponse updateTrainer(Long id, TrainerRequest request) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        trainer.setTrainerCode(request.getTrainerCode());
        trainer.setFirstName(request.getFirstName());
        trainer.setLastName(request.getLastName());
        trainer.setEmail(request.getEmail());
        trainer.setPhone(request.getPhone());
        trainer.setSpecialization(request.getSpecialization());
        trainer.setExperience(request.getExperience());
        trainer.setJoiningDate(request.getJoiningDate());
        trainer.setActive(request.getActive());
        trainer.setUser(user);

        Trainer updatedTrainer = trainerRepository.save(trainer);

        return TrainerMapper.toResponse(updatedTrainer);
    }

    @Override
    public void deleteTrainer(Long id) {

        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));

        trainerRepository.delete(trainer);
    }

}