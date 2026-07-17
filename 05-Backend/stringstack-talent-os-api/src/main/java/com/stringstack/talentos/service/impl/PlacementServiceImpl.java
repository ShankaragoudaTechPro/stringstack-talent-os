package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.placement.PlacementRequest;
import com.stringstack.talentos.dto.placement.PlacementResponse;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.entity.Enrollment;
import com.stringstack.talentos.entity.Placement;
import com.stringstack.talentos.entity.PlacementDrive;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.PlacementMapper;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.repository.EnrollmentRepository;
import com.stringstack.talentos.repository.PlacementDriveRepository;
import com.stringstack.talentos.repository.PlacementRepository;
import com.stringstack.talentos.service.PlacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository placementRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final CompanyRepository companyRepository;

    @Override
    public PlacementResponse createPlacement(PlacementRequest request) {

        if (placementRepository.existsByPlacementCode(request.getPlacementCode())) {
            throw new DuplicateResourceException("Placement Code already exists.");
        }

        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found."));

        PlacementDrive drive = placementDriveRepository.findById(request.getPlacementDriveId())
                .orElseThrow(() -> new ResourceNotFoundException("Placement Drive not found."));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found."));

        Placement placement = PlacementMapper.toEntity(request);

        placement.setEnrollment(enrollment);
        placement.setPlacementDrive(drive);
        placement.setCompany(company);

        Placement savedPlacement = placementRepository.save(placement);

        return PlacementMapper.toResponse(savedPlacement);
    }

    @Override
    public List<PlacementResponse> getAllPlacements() {

        return placementRepository.findAll()
                .stream()
                .map(PlacementMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlacementResponse getPlacementById(Long id) {

        Placement placement = placementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement not found."));

        return PlacementMapper.toResponse(placement);
    }

    @Override
    public PlacementResponse updatePlacement(Long id,
                                             PlacementRequest request) {

        Placement placement = placementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement not found."));

        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found."));

        PlacementDrive drive = placementDriveRepository.findById(request.getPlacementDriveId())
                .orElseThrow(() -> new ResourceNotFoundException("Placement Drive not found."));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found."));

        placement.setPlacementCode(request.getPlacementCode());
        placement.setEnrollment(enrollment);
        placement.setPlacementDrive(drive);
        placement.setCompany(company);
        placement.setInterviewStatus(request.getInterviewStatus());
        placement.setSelectionStatus(request.getSelectionStatus());
        placement.setOfferedPackage(request.getOfferedPackage());
        placement.setJoiningDate(request.getJoiningDate());
        placement.setRemarks(request.getRemarks());
        placement.setActive(request.getActive());

        Placement updatedPlacement = placementRepository.save(placement);

        return PlacementMapper.toResponse(updatedPlacement);
    }

    @Override
    public void deletePlacement(Long id) {

        Placement placement = placementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement not found."));

        placementRepository.delete(placement);
    }
}