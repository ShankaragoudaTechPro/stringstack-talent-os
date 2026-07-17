package com.stringstack.talentos.service.impl;


import com.stringstack.talentos.dto.placementDrive.PlacementDriveRequest;
import com.stringstack.talentos.dto.placementDrive.PlacementDriveResponse;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.entity.PlacementDrive;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.PlacementDriveMapper;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.repository.PlacementDriveRepository;
import com.stringstack.talentos.service.PlacementDriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacementDriveServiceImpl implements PlacementDriveService {

    private final PlacementDriveRepository placementDriveRepository;
    private final CompanyRepository companyRepository;

    @Override
    public PlacementDriveResponse createPlacementDrive(
            PlacementDriveRequest request) {

        if (placementDriveRepository.existsByDriveCode(request.getDriveCode())) {
            throw new DuplicateResourceException("Drive Code already exists.");
        }

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found."));

        PlacementDrive drive = PlacementDriveMapper.toEntity(request);

        drive.setCompany(company);

        PlacementDrive savedDrive =
                placementDriveRepository.save(drive);

        return PlacementDriveMapper.toResponse(savedDrive);
    }

    @Override
    public List<PlacementDriveResponse> getAllPlacementDrives() {

        return placementDriveRepository.findAll()
                .stream()
                .map(PlacementDriveMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlacementDriveResponse getPlacementDriveById(Long id) {

        PlacementDrive drive = placementDriveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Placement Drive not found."));

        return PlacementDriveMapper.toResponse(drive);
    }

    @Override
    public PlacementDriveResponse updatePlacementDrive(
            Long id,
            PlacementDriveRequest request) {

        PlacementDrive drive = placementDriveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Placement Drive not found."));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found."));

        drive.setDriveCode(request.getDriveCode());
        drive.setDriveTitle(request.getDriveTitle());
        drive.setCompany(company);
        drive.setJobRole(request.getJobRole());
        drive.setJobLocation(request.getJobLocation());
        drive.setPackageOffered(request.getPackageOffered());
        drive.setEligibilityCriteria(request.getEligibilityCriteria());
        drive.setDriveDate(request.getDriveDate());
        drive.setLastDateToApply(request.getLastDateToApply());
        drive.setStatus(request.getStatus());
        drive.setActive(request.getActive());

        PlacementDrive updatedDrive =
                placementDriveRepository.save(drive);

        return PlacementDriveMapper.toResponse(updatedDrive);
    }

    @Override
    public void deletePlacementDrive(Long id) {

        PlacementDrive drive = placementDriveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        placementDriveRepository.delete(drive);
    }
}