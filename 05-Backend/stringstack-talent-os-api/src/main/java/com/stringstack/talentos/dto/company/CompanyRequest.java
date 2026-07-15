package com.stringstack.talentos.dto.company;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank
    private String companyCode;

    @NotBlank
    private String companyName;

    @NotBlank
    private String website;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String contactPerson;

    @NotBlank
    private String contactDesignation;

    @NotNull
    private Boolean active;
}