package com.stringstack.talentos.dto.company;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {

    private Long id;

    private String companyCode;

    private String companyName;

    private String website;

    private String email;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String country;

    private String contactPerson;

    private String contactDesignation;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}