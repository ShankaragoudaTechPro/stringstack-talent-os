package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.company.CompanyRequest;
import com.stringstack.talentos.dto.company.CompanyResponse;
import com.stringstack.talentos.entity.Company;

public class CompanyMapper {

    private CompanyMapper(){}

    public static Company toEntity(CompanyRequest request){

        return Company.builder()
                .companyCode(request.getCompanyCode())
                .companyName(request.getCompanyName())
                .website(request.getWebsite())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .contactPerson(request.getContactPerson())
                .contactDesignation(request.getContactDesignation())
                .active(request.getActive())
                .build();
    }

    public static CompanyResponse toResponse(Company company){

        return CompanyResponse.builder()
                .id(company.getId())
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .address(company.getAddress())
                .city(company.getCity())
                .state(company.getState())
                .country(company.getCountry())
                .contactPerson(company.getContactPerson())
                .contactDesignation(company.getContactDesignation())
                .active(company.getActive())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

}