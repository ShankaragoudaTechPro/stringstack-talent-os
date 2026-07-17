package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.batch.BatchRequest;
import com.stringstack.talentos.dto.batch.BatchResponse;
import com.stringstack.talentos.entity.Batch;
import com.stringstack.talentos.entity.Course;
import com.stringstack.talentos.entity.Trainer;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.BatchMapper;
import com.stringstack.talentos.repository.BatchRepository;
import com.stringstack.talentos.repository.CourseRepository;
import com.stringstack.talentos.repository.TrainerRepository;
import com.stringstack.talentos.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    @Override
    public BatchResponse createBatch(BatchRequest request) {

        if (batchRepository.existsByBatchCode(request.getBatchCode())) {
            throw new DuplicateResourceException("Batch Code already exists.");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));

        Batch batch = BatchMapper.toEntity(request);

        batch.setCourse(course);
        batch.setTrainer(trainer);

        Batch savedBatch = batchRepository.save(batch);

        return BatchMapper.toResponse(savedBatch);
    }

    @Override
    public List<BatchResponse> getAllBatches() {

        return batchRepository.findAll()
                .stream()
                .map(BatchMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BatchResponse getBatchById(Long id) {

        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found."));

        return BatchMapper.toResponse(batch);
    }

    @Override
    public BatchResponse updateBatch(Long id, BatchRequest request) {

        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found."));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        Trainer trainer = trainerRepository.findById(request.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found."));

        batch.setBatchCode(request.getBatchCode());
        batch.setBatchName(request.getBatchName());
        batch.setStartDate(request.getStartDate());
        batch.setEndDate(request.getEndDate());
        batch.setCapacity(request.getCapacity());
        batch.setActive(request.getActive());
        batch.setCourse(course);
        batch.setTrainer(trainer);

        Batch updatedBatch = batchRepository.save(batch);

        return BatchMapper.toResponse(updatedBatch);
    }

    @Override
    public void deleteBatch(Long id) {

        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found."));

        batchRepository.delete(batch);
    }

}