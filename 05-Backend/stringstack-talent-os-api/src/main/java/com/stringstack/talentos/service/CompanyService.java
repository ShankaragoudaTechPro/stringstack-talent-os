package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.company.CompanyRequest;
import com.stringstack.talentos.dto.company.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest request);

    List<CompanyResponse> getAllCompanies();

    CompanyResponse getCompanyById(Long id);

    CompanyResponse updateCompany(Long id,
                                  CompanyRequest request);

    void deleteCompany(Long id);

}