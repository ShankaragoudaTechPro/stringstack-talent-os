package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.company.CompanyRequest;
import com.stringstack.talentos.dto.company.CompanyResponse;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.CompanyMapper;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(CompanyRequest request) {

        if (companyRepository.existsByCompanyCode(request.getCompanyCode())) {
            throw new DuplicateResourceException("Company Code already exists.");
        }

        if (companyRepository.existsByCompanyName(request.getCompanyName())) {
            throw new DuplicateResourceException("Company Name already exists.");
        }

        Company company = CompanyMapper.toEntity(request);

        Company savedCompany = companyRepository.save(company);

        return CompanyMapper.toResponse(savedCompany);
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Company not found."));

        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id,
                                         CompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found."));

        company.setCompanyCode(request.getCompanyCode());
        company.setCompanyName(request.getCompanyName());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setAddress(request.getAddress());
        company.setCity(request.getCity());
        company.setState(request.getState());
        company.setCountry(request.getCountry());
        company.setContactPerson(request.getContactPerson());
        company.setContactDesignation(request.getContactDesignation());
        company.setActive(request.getActive());

        Company updatedCompany = companyRepository.save(company);

        return CompanyMapper.toResponse(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found."));

        companyRepository.delete(company);
    }
}